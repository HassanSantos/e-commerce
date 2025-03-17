package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public record OrderController(OrderService orderService) {

    @GetMapping("/create")
    public ResponseEntity<String> listProducts() {

        var orderProducts = List.of(OrderProduct
                        .builder()
                        .id("37c7688f-5c94-4ae1-bf94-e968692a4abe")
                        .quantidade(5)
                        .preco(new BigDecimal("5999.99")).build(),
                OrderProduct
                        .builder()
                        .id("9e3e411a-80d8-48b8-ab30-d7bcb569768d")
                        .quantidade(6)
                        .preco(new BigDecimal("5999.99")).build());
        orderService.createOrder(orderProducts);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/finalizar")
    public ResponseEntity<String> finalizar() {
        orderService.payOrder("f64eea58-5f9d-41d2-b251-078ef2621cef");
        return ResponseEntity.ok("ok");
    }
}
