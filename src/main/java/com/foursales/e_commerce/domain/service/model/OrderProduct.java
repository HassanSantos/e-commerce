package com.foursales.e_commerce.domain.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class OrderProduct {

    private String id;
    private Integer quantidade;
    private BigDecimal preco;
}
