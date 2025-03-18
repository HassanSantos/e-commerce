package com.foursales.e_commerce.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class OrderProductDto {

    private String id;
    private Integer quantidade;
    private BigDecimal value;
}
