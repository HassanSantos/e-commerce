package com.foursales.e_commerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderDto {
    private List<OrderProductDto> products;
}
