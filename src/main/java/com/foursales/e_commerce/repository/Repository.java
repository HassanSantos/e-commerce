package com.foursales.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repository extends JpaRepository<Produtos, UUID> {
}
