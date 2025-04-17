package com.example.kutuphane.Services.Impl;

import com.example.kutuphane.Entities.Author;
import com.example.kutuphane.Repositories.AuthorRepository;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public GenericResponse<?> saveAuthor(Author author) {
        System.out.println("saveAuthor called...");

        if (author.getId() != null && authorRepository.existsById(author.getId())) {
            return GenericResponse.error(Constants.FOUND_ID);
        }

        Author saved = authorRepository.save(author);
        return GenericResponse.success(saved);
    }

    @Override
    public GenericResponse<?> getAllAuthors() {
        System.out.println("getAllAuthors called...");

        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        } else {
            return GenericResponse.success(authors);
        }
    }

    @Override
    public GenericResponse<?> getAuthorById(Integer id) {
        System.out.println("getAuthorById called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            return GenericResponse.success(optional.get());
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> updateAuthor(Integer id, Author updatedAuthor) {
        System.out.println("updateAuthor called...");

        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            Author dbAuthor = optional.get();
            dbAuthor.setAd(updatedAuthor.getAd());
            dbAuthor.setSoyad(updatedAuthor.getSoyad());

            Author updated = authorRepository.save(dbAuthor);
            return GenericResponse.success(updated);
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
}
