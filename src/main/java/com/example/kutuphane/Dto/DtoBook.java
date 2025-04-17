package com.example.kutuphane.Dto;

import com.example.kutuphane.Entities.Author;
import com.example.kutuphane.Entities.Durum;
import com.example.kutuphane.Entities.Publisher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class DtoBook {
    private Integer id;


    private String isbn;


    private String ad;


    private int baskiYili;


    private Durum durum;

   private DtoAuthor dtoAuthor;
   private DtoPublisher dtoPublisher;


}
