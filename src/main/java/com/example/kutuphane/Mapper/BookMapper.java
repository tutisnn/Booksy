package com.example.kutuphane.Mapper;
import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper  {
    DtoBook bookToDtoBook(Book book);
    Book  DtoBookToBook(DtoBook dtoBook);

}
