package com.example.kutuphane.Controllers;

import com.example.kutuphane.Dto.DtoAuthorIU;
import com.example.kutuphane.Entities.Author;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IAuthorController {
    GenericResponse<?> saveAuthor(DtoAuthorIU dto);
    GenericResponse<?> getAllAuthors();
    GenericResponse<?> getAuthorById(Integer id);
    GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto);
    GenericResponse<?> deleteAuthor(Integer id);
    GenericResponse<?> getBooksByAuthorId(Integer authorId);
}
