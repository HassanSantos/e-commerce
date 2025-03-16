package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, UUID> {
    List<OrderProductEntity> findByOrderEntity_Id(String id);
}
