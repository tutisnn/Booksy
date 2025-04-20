package com.example.kutuphane.Controllers.Impl;

import com.example.kutuphane.Controllers.IAuthorController;
import com.example.kutuphane.Dto.DtoAuthorIU;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Author")
public class AuthorController implements IAuthorController {

    @Autowired
    private IAuthorService authorService;

    @PostMapping("/save")
    @Override
    public GenericResponse<?> saveAuthor(@RequestBody DtoAuthorIU dto) {
        return authorService.saveAuthor(dto);
    }

    @GetMapping("/listAllAuthors")
    @Override
    public GenericResponse<?> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/list/{id}")
    @Override
    public GenericResponse<?> getAuthorById(@PathVariable(name = "id") Integer id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public GenericResponse<?> updateAuthor(@PathVariable(name = "id") Integer id,
                                           @RequestBody DtoAuthorIU dto) {
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deleteAuthor(@PathVariable(name = "id") Integer id) {
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/{authorId}/books")
    @Override
    public GenericResponse<?> getBooksByAuthorId(@PathVariable(name = "authorId") Integer authorId) {
        return authorService.getBooksByAuthorId(authorId);
    }
}
