package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Books;
@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    @Query("FROM Books c WHERE c.name LIKE %?1%")
    public List<Books> search(String keyword);
    @Query("FROM Books b WHERE b.category_id = :category_id")
    public List<Books> findByCategory_id(int category_id);
    public List<Books> findByBrand_id(int brandId);
}

