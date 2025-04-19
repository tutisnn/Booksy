package com.example.kutuphane.Controllers;

import com.example.kutuphane.Dto.DtoBookIU;
import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IBookController {
    GenericResponse<?>saveBook(DtoBookIU dto, MultipartFile file);
    GenericResponse<?>updateBook(Integer id, DtoBookIU dto, MultipartFile file);
    GenericResponse<?>deleteBook(Integer id);
    GenericResponse<?>getAllBooks();

    GenericResponse<?> findById(Integer id);
    GenericResponse<?> searchBooksByName(String keyword);



}
