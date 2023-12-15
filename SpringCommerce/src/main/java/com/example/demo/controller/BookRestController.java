package com.example.demo.controller;

import com.example.demo.model.Books;
import com.example.demo.model.ResponseObject;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Products")
public class BookRestController {
    @Autowired
    private BookService bookService;
    @GetMapping("")
    public List<Books> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Integer id){
        return bookService.getById(id);
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertBook(@RequestBody Books newBook){
        return bookService.addBook(newBook);
    }
    @PostMapping("{id}")
    ResponseEntity<ResponseObject> updateBook(@RequestBody Books newBook,@PathVariable Integer id){
        return bookService.updateBook(newBook,id);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteBook(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }
}
