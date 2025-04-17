package com.example.kutuphane.Controllers.Impl;

import com.example.kutuphane.Controllers.IBookController;
import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Book")
public class BookController implements IBookController {
    @Autowired
    private IBookService bookService;

    @PostMapping(path = "/save")
    @Override
    public GenericResponse<?> saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    @PutMapping("/update/{id}")
    @Override
    public GenericResponse<?> updateBook(@PathVariable(name="id") Integer id,
                                         @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deleteBook(@PathVariable(name="id")Integer id) {
        return bookService.deleteBook(id);
    }
    @GetMapping(path ="/listAll")
    @Override
    public GenericResponse<?> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path ="/list/{id}")
    @Override
    public GenericResponse<?> findById(@PathVariable(name="id") Integer id) {
        return bookService.findById(id);
    }
}
