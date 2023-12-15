package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.Brand;
import com.example.demo.model.ResponseObject;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BooksServiceImpl implements BookService{
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Books createNew(Books books) throws Exception {
        return null;
    }

    @Override
    public List<Books> getAll() {
        return booksRepository.findAll();
    }

    @Override
    public Books findById(Integer id) {
        return booksRepository.findById(id).get();
    }
    @Override
    public ResponseEntity<ResponseObject> getById(Integer id) {
        Optional<Books> fondProduct = booksRepository.findById(id);
        return fondProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "Successful!",
                                "Query product successfully",
                                fondProduct
                        )
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(
                                "Failed",
                                "Can not find product with id = " + id,
                                ""
                        )
                );
    }

    @Override
    public ResponseEntity<ResponseObject> addBook(Books books) {
        Integer idBrand = books.getBrand().getId();
        Brand foundBrand = brandRepository.findById(idBrand).get();
        if (foundBrand == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(
                            "failed!",
                            "Not found brand",
                            ""
                    )
            );
        }
        Books newBook = new Books();
        newBook.setName(books.getName());
        newBook.setPrice(books.getPrice());
        newBook.setDescription(books.getDescription());
        newBook.setCategory_id(books.getCategory_id());
        newBook.setQuantity(books.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful!",
                        "Insert product successfully",
                        booksRepository.save(newBook)
                )
        );
    }

    @Override
    public ResponseEntity<ResponseObject> updateBook(Books newBook, Integer id) {
        Books updatesBook = booksRepository.findById(id)
                .map(book ->{
                    book.setName(newBook.getName());
                    book.setPrice(newBook.getPrice());
                    book.setBrand(newBook.getBrand());
                    book.setCategory_id(newBook.getCategory_id());
                    return booksRepository.save(book);
                }).orElseGet(() -> {
                    newBook.setId(id);
                    return booksRepository.save(newBook);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful",
                        "Update product successfully",
                        updatesBook
                )
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteBook(Integer id) {
        boolean exist = booksRepository.existsById(id);
        Optional<Books> delBook = booksRepository.findById(id);
        if (exist && delBook.isPresent()){
            booksRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "Successful",
                            "Delete product successfully!",
                            delBook
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(
                        "Failed",
                        "Cannot find product to delete!",
                        ""
                )
        );
    }
}
