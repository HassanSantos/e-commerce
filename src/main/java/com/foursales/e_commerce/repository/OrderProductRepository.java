package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, String> {
    List<OrderProductEntity> findByOrderEntity_Id(String id);

    boolean existsByProduct_Id(String id);
}
