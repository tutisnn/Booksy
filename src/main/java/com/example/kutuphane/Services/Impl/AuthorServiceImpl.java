package com.example.kutuphane.Services.Impl;

import com.example.kutuphane.Dto.DtoAuthor;
import com.example.kutuphane.Dto.DtoAuthorIU;
import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Entities.Author;
import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.Mapper.AuthorMapper;
import com.example.kutuphane.Mapper.BookMapperView;
import com.example.kutuphane.Repositories.AuthorRepository;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorMapper authorMapper;

    private final BookMapperView bookMapperView = new BookMapperView();

    @Override
    public GenericResponse<?> saveAuthor(DtoAuthorIU dto) {
        System.out.println("saveAuthor called...");

        Author author = authorMapper.dtoToAuthor(dto);
        Author saved = authorRepository.save(author);
        return GenericResponse.success(authorMapper.authorToDto(saved));
    }

    @Override
    public GenericResponse<?> getAllAuthors() {
        System.out.println("getAllAuthors called...");

        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        }

        List<DtoAuthor> dtoList = authors.stream()
                .map(authorMapper::authorToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }

    @Override
    public GenericResponse<?> getAuthorById(Integer id) {
        System.out.println("getAuthorById called...");

        Optional<Author> optional = authorRepository.findById(id);
        return optional.map(author -> GenericResponse.success(authorMapper.authorToDto(author)))
                .orElseGet(() -> GenericResponse.error(Constants.EMPTY_ID));
    }

    @Override
    public GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto) {
        System.out.println("updateAuthor called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            Author dbAuthor = optional.get();
            authorMapper.updateAuthorFromDto(dto, dbAuthor);
            Author updated = authorRepository.save(dbAuthor);
            return GenericResponse.success(authorMapper.authorToDto(updated));
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> deleteAuthor(Integer id) {
        System.out.println("deleteAuthor called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            authorRepository.delete(optional.get());
            return GenericResponse.success("Yazar başarıyla silindi.");
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByAuthorId(Integer authorId) {
        System.out.println("getBooksByAuthorId called...");

        if (!authorRepository.existsById(authorId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        List<Book> books = bookRepository.findByAuthorId(authorId);
        if (books.isEmpty()) {
            return GenericResponse.error("Bu yazara ait kitap bulunamadı.");
        }

        List<DtoBook> dtoList = books.stream()
                .filter(book -> book.getAuthor() != null && book.getPublisher() != null && book.getCategory() != null)
                .map(bookMapperView::bookToDto)
                .collect(Collectors.toList());

        return GenericResponse.success(dtoList);
    }
}
