package com.example.kutuphane.Mapper;

import com.example.kutuphane.Dto.DtoBookIU;
import com.example.kutuphane.Entities.Book;
import org.springframework.stereotype.Component;


public class BookMapperIU {

    public static Book dtoToBook(DtoBookIU dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setAd(dto.getAd());
        book.setBaskiYili(dto.getBaskiYili());
        book.setDurum(dto.getDurum());
        book.setDil(dto.getDil());

        return book;
    }

    public static void updateBookFromDto(DtoBookIU dto, Book book) {
        if (dto == null || book == null) return;

        if (dto.getIsbn() != null) book.setIsbn(dto.getIsbn());
        if (dto.getAd() != null) book.setAd(dto.getAd());
        if (dto.getBaskiYili() != 0) book.setBaskiYili(dto.getBaskiYili());
        if (dto.getDurum() != null) book.setDurum(dto.getDurum());
        if (dto.getDil() != null) book.setDil(dto.getDil());

    }
}
