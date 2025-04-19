package com.example.kutuphane.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
public class Book {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="id")
 private Integer id;

 @Column(name="isbn", nullable=false, unique=true)
 private String isbn;

 @Column(name="ad", nullable=false)
 private String ad;

 @Column(name="baski_yili")
 private int baskiYili;

 @Enumerated(EnumType.STRING)
 @Column(name="durum")
 private Durum durum;

 @Column(name="kapak_fotosu_url")
 private String kitapKapakfotosuUrl;
 @Column(name = "dil")
 private String dil;

 @ManyToOne
 @JoinColumn(name = "yazar_id")
 private Author author;

 @ManyToOne
 @JoinColumn(name = "yayinci_id")
 private Publisher publisher;

 @ManyToOne
 @JoinColumn(name = "kategori_id")
 private Category category;
}





