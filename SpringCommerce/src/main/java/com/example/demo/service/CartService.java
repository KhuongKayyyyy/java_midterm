package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.model.Cart;
import com.example.demo.model.User;

import java.util.List;

public interface CartService {
    public void addToCart(User user, int bookId);
    public Cart getCart(User user);
    public void removeFromCart(User user,int bookId);
    public void clearCart(User user);
}
