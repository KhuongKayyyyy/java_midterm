package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createNew(Category category);
    List<Category> getAll();
    Optional<Category> getCategory(Integer id);
}
