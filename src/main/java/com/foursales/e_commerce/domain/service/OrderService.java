package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.domain.service.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(Order order);
    Order payOrder(UUID orderId);
    List<Order> getOrdersByUser(UUID userId);
}
