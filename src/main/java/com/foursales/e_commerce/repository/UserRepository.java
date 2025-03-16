package com.foursales.e_commerce.repository;

import com.foursales.e_commerce.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
