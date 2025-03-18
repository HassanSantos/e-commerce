package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(List<OrderProduct> order, String user);
    void payOrder(String orderId);
    List<UserOrderCountDTO> getTop5Users();
    List<UserAverageTicketDTO> getAverageTiket();
    List<Order> getOrdersByUser(UUID userId);
}
