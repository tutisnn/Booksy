package com.example.kutuphane.Services.Impl;

import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Dto.DtoBookIU;
import com.example.kutuphane.Entities.*;
import com.example.kutuphane.Mapper.BookMapperIU;
import com.example.kutuphane.Mapper.BookMapperView;
import com.example.kutuphane.Repositories.AuthorRepository;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.Repositories.CategoryRepository;
import com.example.kutuphane.Repositories.PublisherRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private final BookMapperIU bookMapperIU = new BookMapperIU();
    private final BookMapperView bookMapperView = new BookMapperView();

    @Override
    public GenericResponse<?> saveBook(DtoBookIU dto, MultipartFile file) {
        System.out.println("saveBook called...");

        Author author = authorRepository.findByAdAndSoyad(dto.getAuthorAd(), dto.getAuthorSoyad())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAd(dto.getAuthorAd());
                    newAuthor.setSoyad(dto.getAuthorSoyad());
                    return authorRepository.save(newAuthor);
                });

        Publisher publisher = publisherRepository.findByAd(dto.getPublisherAd())
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher();
                    newPublisher.setAd(dto.getPublisherAd());
                    return publisherRepository.save(newPublisher);
                });

        Category category = categoryRepository.findByAd(dto.getCategoryAd())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setAd(dto.getCategoryAd());
                    return categoryRepository.save(newCategory);
                });

        Book book = bookMapperIU.dtoToBook(dto);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategory(category);

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images", fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            book.setKitapKapakfotosuUrl("/images/" + fileName);
        } catch (IOException e) {
            return GenericResponse.error("Fotoğraf yüklenemedi: " + e.getMessage());
        }

        Book saved = bookRepository.save(book);
        DtoBook responseDto = bookMapperView.bookToDto(saved);
        return GenericResponse.success(responseDto);
    }

    @Override
    public GenericResponse<?> updateBook(Integer id, DtoBookIU dto, MultipartFile file) {
        System.out.println("updateBook called with DTO...");

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        Book book = optional.get();
        bookMapperIU.updateBookFromDto(dto, book);

        Author author = authorRepository.findByAdAndSoyad(dto.getAuthorAd(), dto.getAuthorSoyad())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setAd(dto.getAuthorAd());
                    newAuthor.setSoyad(dto.getAuthorSoyad());
                    return authorRepository.save(newAuthor);
                });

        Publisher publisher = publisherRepository.findByAd(dto.getPublisherAd())
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher();
                    newPublisher.setAd(dto.getPublisherAd());
                    return publisherRepository.save(newPublisher);
                });

        Category category = categoryRepository.findByAd(dto.getCategoryAd())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setAd(dto.getCategoryAd());
                    return categoryRepository.save(newCategory);
                });

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategory(category);

        if (file != null && !file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images", fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                book.setKitapKapakfotosuUrl("/images/" + fileName);
            } catch (IOException e) {
                return GenericResponse.error("Fotoğraf güncellenemedi: " + e.getMessage());
            }
        }

        Book updated = bookRepository.save(book);
        DtoBook response = bookMapperView.bookToDto(updated);
        return GenericResponse.success(response);
    }

    @Override
    public GenericResponse<?> deleteBook(Integer id) {
        System.out.println("deleteBook called...");

        Book bookExists = bookRepository.findBookById(id);
        if (bookExists == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        } else {
            bookRepository.delete(bookExists);
            return GenericResponse.success("Kitap başarıyla silindi.");
        }
    }

    @Override
    public GenericResponse<?> getAllBooks() {
        System.out.println("getAllBooks called...");
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        }

        List<DtoBook> dtoBooks = books.stream()
                .filter(book -> book.getAuthor() != null && book.getPublisher() != null && book.getCategory() != null)
                .map(bookMapperView::bookToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoBooks);
    }

    @Override
    public GenericResponse<?> findById(Integer id) {
        Book book = bookRepository.findBookById(id);

        if (book == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        DtoBook dto = bookMapperView.bookToDto(book);
        return GenericResponse.success(dto);
    }

    @Override
    public GenericResponse<?> searchBooksByName(String keyword) {
        System.out.println("searchBooksByName called...");

        List<Book> foundBooks = bookRepository.searchByName(keyword);

        if (foundBooks.isEmpty()) {
            return GenericResponse.error("Aradığınız kelimeyle eşleşen kitap bulunamadı.");
        }

        List<DtoBook> dtoList = foundBooks.stream()
                .filter(book -> book.getAuthor() != null && book.getPublisher() != null && book.getCategory() != null)
                .map(bookMapperView::bookToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }
}
