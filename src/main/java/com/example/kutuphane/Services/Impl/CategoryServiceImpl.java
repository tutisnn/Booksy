package com.example.kutuphane.Services.Impl;

import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.Entities.Category;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.Repositories.CategoryRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public GenericResponse<?> saveCategory(Category category) {
        System.out.println("saveCategory called...");

        if (category.getId() != null && categoryRepository.existsById(category.getId())) {
            return GenericResponse.error(Constants.FOUND_ID);
        }

        Category saved = categoryRepository.save(category);
        return GenericResponse.success(saved);
    }

    @Override
    public GenericResponse<?> getAllCategories() {
        System.out.println("getAllCategories called...");

        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return GenericResponse.error(Constants.EMPTY_LIST);
        } else {
            return GenericResponse.success(categories);
        }
    }

    @Override
    public GenericResponse<?> getCategoryById(Integer id) {
        System.out.println("getCategoryById called...");

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            return GenericResponse.success(optional.get());
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> updateCategory(Integer id, Category updatedCategory) {
        System.out.println("updateCategory called...");

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category dbCategory = optional.get();
            dbCategory.setAd(updatedCategory.getAd());

            Category updated = categoryRepository.save(dbCategory);
            return GenericResponse.success(updated);
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> deleteCategory(Integer id) {
        System.out.println("deleteCategory called...");

        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
            return GenericResponse.success("Kategori başarıyla silindi.");
        } else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByCategoryId(Integer categoryId) {
        System.out.println("getBooksByCategoryId called...");

        if (!categoryRepository.existsById(categoryId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }

        List<Book> books = bookRepository.findByCategoryId(categoryId);
        if (books.isEmpty()) {
            return GenericResponse.error("Bu kategoriye ait kitap bulunamadı.");
        } else {
            return GenericResponse.success(books);
        }
    }
}
