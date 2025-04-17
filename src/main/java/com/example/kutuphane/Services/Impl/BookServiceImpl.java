package com.example.kutuphane.Services.Impl;


import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IBookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;



    @Override
    public GenericResponse<?> saveBook(Book book) {
        System.out.println("saveBook called...");


        if (book.getId() != null && bookRepository.existsById(book.getId())) {
            return GenericResponse.error(Constants.FOUND_ID);
        }


        Book saved = bookRepository.save(book);
        return GenericResponse.success(saved);
    }


    @Override
    public GenericResponse<?> updateBook(Integer id,Book updatedBook) {
        System.out.println("updateBook called...");

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            Book dbBook = optional.get();
            dbBook.setAd(updatedBook.getAd());
            dbBook.setDurum(updatedBook.getDurum());
            dbBook.setAuthor(updatedBook.getAuthor());
            dbBook.setIsbn(updatedBook.getIsbn());
            dbBook.setBaskiYili(updatedBook.getBaskiYili());
            dbBook.setPublisher(updatedBook.getPublisher());

            Book updated = bookRepository.save(dbBook);
            return GenericResponse.success(updated);

        }else{

                return GenericResponse.error(Constants.EMPTY_ID);

        }
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
        } else {
            return GenericResponse.success(books);
        }
    }



    @Override
    public GenericResponse<?> findById(Integer id) {
        Book bookExists = bookRepository.findBookById(id);
        if (bookExists == null) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }else{
            return GenericResponse.success(bookExists);
        }
    }
}
