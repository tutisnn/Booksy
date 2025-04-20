package com.example.kutuphane.Controllers;

import com.example.kutuphane.Dto.DtoCategoryIU;
import com.example.kutuphane.Entities.Category;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface ICategoryController {
    GenericResponse<?> saveCategory(DtoCategoryIU dto);
    GenericResponse<?> getAllCategories();
    GenericResponse<?> getCategoryById(Integer id);
    GenericResponse<?> updateCategory(Integer id, DtoCategoryIU dto);
    GenericResponse<?> deleteCategory(Integer id);
    GenericResponse<?> getBooksByCategoryId(Integer categoryId);
}
