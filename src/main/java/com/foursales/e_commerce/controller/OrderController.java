package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record OrderController(OrderService orderService) {

    @GetMapping("/create")
    public ResponseEntity<String> listProducts() {
        orderService.createOrder(new Order());
        return ResponseEntity.ok("ok");
    }
}
