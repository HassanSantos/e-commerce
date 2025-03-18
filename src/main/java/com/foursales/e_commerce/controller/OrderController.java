package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.domain.service.OrderService;
import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderModel;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller()
@RequestMapping(value = "/orders")
public record OrderController(OrderService orderService) {

    @PostMapping("/create")
    public ResponseEntity<String> listProducts(@RequestBody List<OrderProduct> orderProducts, @RequestHeader String user) {
        orderService.createOrder(orderProducts, user);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/finalizar/{id}")
    public ResponseEntity<String> finalizar(@PathVariable String id) {
        orderService.payOrder(id);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/top")
    public ResponseEntity<List<UserOrderCountDTO>> findalizar() {
        return ResponseEntity.ok(orderService.getTop5Users());
    }

    @GetMapping("/average-ticket")
    public ResponseEntity<List<UserAverageTicketDTO>> averageTiket() {
        return ResponseEntity.ok(orderService.getAverageTiket());
    }

    @GetMapping("/total-value")
    public ResponseEntity<BigDecimal> totalAmountInvoicedPeriod(@RequestParam("startDate") LocalDate startDate,
                                                                @RequestParam("endDate") LocalDate endDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return ResponseEntity.ok(orderService.totalAmountInvoicedPeriod(startDate, endDate));
    }

    @GetMapping("/current-user")
    public ResponseEntity<List<OrderProduct>> ordersUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(orderService.ordersByUser(authentication.getPrincipal().toString()));
    }
}
