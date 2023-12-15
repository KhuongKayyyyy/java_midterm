package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.net.Inet4Address;
import java.util.List;

public interface BookService {
    Books createNew(Books books) throws Exception;
    List<Books> getAll();
    Books findById(Integer id);
    ResponseEntity<ResponseObject> getById(Integer id);
    ResponseEntity<ResponseObject> addBook(Books books);
    ResponseEntity<ResponseObject> updateBook(Books books, Integer id);
    ResponseEntity<ResponseObject> deleteBook(Integer id);
}
