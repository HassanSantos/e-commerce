package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(List<OrderProduct> order);
    Order payOrder(String orderId);
    List<Order> getOrdersByUser(UUID userId);
}
