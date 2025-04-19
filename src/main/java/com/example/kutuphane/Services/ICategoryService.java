package com.example.kutuphane.Services;

import com.example.kutuphane.Entities.Category;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface ICategoryService {

    GenericResponse<?> saveCategory(Category category);
    GenericResponse<?> getAllCategories();
    GenericResponse<?> getCategoryById(Integer id);
    GenericResponse<?> updateCategory(Integer id, Category category);
    GenericResponse<?> deleteCategory(Integer id);
    GenericResponse<?> getBooksByCategoryId(Integer categoryId);
}
