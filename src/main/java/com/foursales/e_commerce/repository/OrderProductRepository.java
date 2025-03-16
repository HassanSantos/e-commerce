package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, UUID> {
}
