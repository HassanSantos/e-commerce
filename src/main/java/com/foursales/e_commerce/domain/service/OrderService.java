package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.dto.OrderDto;
import com.foursales.e_commerce.dto.OrderProductDto;
import com.foursales.e_commerce.dto.UserAverageTicketDTO;
import com.foursales.e_commerce.dto.UserOrderCountDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(List<OrderProductDto> order, String user);
    void payOrder(String orderId);
    List<UserOrderCountDTO> getTop5Users();
    List<UserAverageTicketDTO> getAverageTiket();
    BigDecimal totalAmountInvoicedPeriod(LocalDate startDate, LocalDate endDate);
    List<OrderProductDto> ordersByUser(String email);
}
