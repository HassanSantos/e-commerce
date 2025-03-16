package com.foursales.e_commerce.domain.service.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pedido {

    private String id;
    private Integer quantidade;
}
