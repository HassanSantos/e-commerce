package com.foursales.e_commerce.comum;

import com.foursales.e_commerce.domain.service.model.Order;
import com.foursales.e_commerce.repository.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderEntityToOrder(OrderEntity orderEntity);
}
