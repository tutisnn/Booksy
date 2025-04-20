package com.example.kutuphane.Mapper;

import com.example.kutuphane.Dto.DtoBook;
import com.example.kutuphane.Entities.Book;
import org.springframework.stereotype.Component;


public class BookMapperView {

    public DtoBook bookToDto(Book book) {
        if (book == null) return null;

        DtoBook dto = new DtoBook();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setAd(book.getAd());
        dto.setBaskiYili(book.getBaskiYili());
        dto.setDurum(book.getDurum());
        dto.setKitapKapakfotosuUrl(book.getKitapKapakfotosuUrl());
        dto.setDil(book.getDil());
        dto.setAuthorAd(book.getAuthor().getAd());
        dto.setAuthorSoyad(book.getAuthor().getSoyad());
        dto.setPublisherAd(book.getPublisher().getAd());
        dto.setCategoryAd(book.getCategory().getAd());

        return dto;
    }
}
