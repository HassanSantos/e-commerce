package com.foursales.e_commerce.infrastructure.service.repository;

import com.foursales.e_commerce.infrastructure.service.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

}
