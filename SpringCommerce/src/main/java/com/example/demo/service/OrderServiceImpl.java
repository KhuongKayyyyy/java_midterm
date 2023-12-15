package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Order convertCartToOrder(Cart cart, Long orderValue) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderValue(orderValue);
        orderRepository.save(order);
        List<OrderDetail> orderDetails = cart.getBooks().stream()
                .map(books -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setBooks(books);
                    orderDetail.setQuantity(2);
                    orderDetail.setOrder(order);
                    return orderDetailRepository.save(orderDetail);
                })
                .collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
