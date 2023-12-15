package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Books;
import com.example.demo.payroll.BooksNotFoundException;
import com.example.demo.repository.BooksRepository;
@RestController
@RequestMapping("/books/api")
public class BooksAPIController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("list")
    public List<Books> list() {
        return booksRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public Books one(@PathVariable Integer id) {
        return booksRepository.findById(id).orElseThrow(()->new BooksNotFoundException(id));
    }
}
