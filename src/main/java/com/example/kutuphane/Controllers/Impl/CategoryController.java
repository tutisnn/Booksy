package com.example.kutuphane.Controllers.Impl;

import com.example.kutuphane.Controllers.ICategoryController;
import com.example.kutuphane.Dto.DtoCategoryIU;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Category")
public class CategoryController implements ICategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/save")
    @Override
    public GenericResponse<?> saveCategory(@RequestBody DtoCategoryIU dto) {
        return categoryService.saveCategory(dto);
    }

    @GetMapping("/listAllCategories")
    @Override
    public GenericResponse<?> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/list/{id}")
    @Override
    public GenericResponse<?> getCategoryById(@PathVariable(name = "id") Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public GenericResponse<?> updateCategory(@PathVariable(name = "id") Integer id,
                                             @RequestBody DtoCategoryIU dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deleteCategory(@PathVariable(name = "id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{categoryId}/books")
    @Override
    public GenericResponse<?> getBooksByCategoryId(@PathVariable(name = "categoryId") Integer categoryId) {
        return categoryService.getBooksByCategoryId(categoryId);
    }
}
