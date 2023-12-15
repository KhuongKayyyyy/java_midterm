package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.User;

import java.util.List;

public interface OrderService {
    public Order convertCartToOrder(Cart cart, Long orderValue);
    public List<Order> getOrderByUser(User user);
}
