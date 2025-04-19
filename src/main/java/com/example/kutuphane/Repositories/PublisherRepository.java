package com.example.kutuphane.Repositories;

import com.example.kutuphane.Entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findPublisherById(int id);

    Optional<Publisher> findByAd(String publisherAd);
}
