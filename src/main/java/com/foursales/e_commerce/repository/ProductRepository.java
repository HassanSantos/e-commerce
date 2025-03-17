package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProdutoEntity, String> {
}
