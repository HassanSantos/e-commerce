package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {


}
