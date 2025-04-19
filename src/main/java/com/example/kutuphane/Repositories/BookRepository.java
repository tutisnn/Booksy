package com.example.kutuphane.Repositories;

import com.example.kutuphane.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(int id);
    List<Book> findByAuthorId(Integer authorId);
    List<Book> findByPublisherId(Integer publisherId);
    List<Book> findByCategoryId(Integer categoryId);
    @Query(value = "SELECT * FROM book WHERE ad ILIKE %:keyword%", nativeQuery = true)
    List<Book> searchByName(@Param("keyword") String keyword);



}
