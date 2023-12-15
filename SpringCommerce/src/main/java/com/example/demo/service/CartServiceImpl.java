package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.BooksRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public void addToCart(User user, int bookId) {
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setBooks(new ArrayList<>());
            cartRepository.save(cart);
        }
        // Find the book by ID
        Books book = booksRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        if (cart.getBooks().contains(book)){
            return;
        }
        cart.getBooks().add(book);
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(User user) {
        Cart cart = cartRepository.findByUser(user);
//        return cart != null ? cart.getBooks() : Collections.emptyList();
        return  cart;
    }

    @Override
    public void removeFromCart(User user, int bookId) {
        Cart cart = cartRepository.findByUser(user);
        Books bookToRemove = booksRepository.findById(bookId).get();
        cart.getBooks().remove(bookToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = cartRepository.findByUser(user);
        cart.getBooks().clear();
        cartRepository.save(cart);
    }
}
