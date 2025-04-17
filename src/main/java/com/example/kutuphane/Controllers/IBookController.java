package com.example.kutuphane.Controllers;

import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IBookController {
    GenericResponse<?> saveBook(Book book);
    GenericResponse<?>updateBook(Integer id, Book book);
    GenericResponse<?>deleteBook(Integer id);
    GenericResponse<?>getAllBooks();

    GenericResponse<?> findById(Integer id);
}
