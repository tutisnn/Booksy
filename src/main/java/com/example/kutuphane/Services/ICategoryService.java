package com.example.kutuphane.Services;

import com.example.kutuphane.Dto.DtoCategory;
import com.example.kutuphane.Dto.DtoCategoryIU;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface ICategoryService {

    GenericResponse<?> saveCategory(DtoCategoryIU dto);
    GenericResponse<?> getAllCategories();
    GenericResponse<?> getCategoryById(Integer id);
    GenericResponse<?> updateCategory(Integer id, DtoCategoryIU dto);
    GenericResponse<?> deleteCategory(Integer id);
    GenericResponse<?> getBooksByCategoryId(Integer categoryId);
}