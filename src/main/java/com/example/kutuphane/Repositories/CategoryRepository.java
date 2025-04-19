package com.example.kutuphane.Repositories;

import com.example.kutuphane.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);

    Optional<Category> findByAd(String categoryAd);

}
