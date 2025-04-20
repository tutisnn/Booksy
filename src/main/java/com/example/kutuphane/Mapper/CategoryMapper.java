package com.example.kutuphane.Mapper;

import com.example.kutuphane.Dto.DtoCategory;
import com.example.kutuphane.Dto.DtoCategoryIU;
import com.example.kutuphane.Entities.Category;
import org.springframework.stereotype.Component;


public class CategoryMapper {

    public static Category dtoToCategory(DtoCategoryIU dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setAd(dto.getAd());
        return category;
    }

    public static DtoCategory categoryToDto(Category category) {
        if (category == null) return null;

        DtoCategory dto = new DtoCategory();
        dto.setId(category.getId());
        dto.setAd(category.getAd());
        return dto;
    }

    public static void updateCategoryFromDto(DtoCategoryIU dto, Category category) {
        if (dto == null || category == null) return;

        if (dto.getAd() != null) {
            category.setAd(dto.getAd());
        }
    }
}
