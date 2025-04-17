package com.example.kutuphane.Services;

import com.example.kutuphane.Entities.Author;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IAuthorService {
    GenericResponse<?>saveAuthor(Author author);
    GenericResponse<?>getAllAuthors();
    GenericResponse<?>getAuthorById(Integer id);
    GenericResponse<?>updateAuthor(Integer id,Author author);
    GenericResponse<?>deleteAuthor(Integer id);



}
