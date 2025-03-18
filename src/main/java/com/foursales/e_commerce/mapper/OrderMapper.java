package com.foursales.e_commerce.mapper;

import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.domain.service.model.OrderProduct;
import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderEntityToOrder(OrderEntity orderEntity);
    List<OrderProduct> orderEntityToOrder(List<OrderEntity> orderEntity);
}
