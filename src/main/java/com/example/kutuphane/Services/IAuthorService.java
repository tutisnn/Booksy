package com.example.kutuphane.Services;

import com.example.kutuphane.Dto.DtoAuthor;
import com.example.kutuphane.Dto.DtoAuthorIU;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IAuthorService {
    GenericResponse<?> saveAuthor(DtoAuthorIU dto);
    GenericResponse<?> getAllAuthors();
    GenericResponse<?> getAuthorById(Integer id);
    GenericResponse<?> updateAuthor(Integer id, DtoAuthorIU dto);
    GenericResponse<?> deleteAuthor(Integer id);
    GenericResponse<?> getBooksByAuthorId(Integer authorId);
}