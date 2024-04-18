package com.example.eco_market.Services;

import com.example.eco_market.Models.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}