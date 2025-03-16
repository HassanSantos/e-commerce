package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public record OrderController(OrderService orderService) {

    @GetMapping("/create")
    public ResponseEntity<String> listProducts() {
        orderService.createOrder(new Order());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/finalizar")
    public ResponseEntity<String> finalizar() {
        orderService.payOrder("40276234-874c-4d9d-b65a-f8016c10003b");
        return ResponseEntity.ok("ok");
    }
}
