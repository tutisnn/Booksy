package com.example.kutuphane.Dto;

import com.example.kutuphane.Entities.Durum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBook {
    private Integer id;
    private String isbn;
    private String ad;
    private int baskiYili;
    private Durum durum;
    private String kitapKapakfotosuUrl;
    private String dil;

    private String authorAd;
    private String authorSoyad;

    private String publisherAd;

    private String categoryAd;
}
