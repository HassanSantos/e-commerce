package com.foursales.e_commerce.mapper;

import com.foursales.e_commerce.dto.OrderDto;
import com.foursales.e_commerce.dto.OrderProductDto;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderEntityToOrder(OrderEntity orderEntity);
    List<OrderProductDto> orderEntityToOrder(List<OrderEntity> orderEntity);
}
