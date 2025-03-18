package com.foursales.e_commerce.dto;


import com.foursales.e_commerce.infrastructure.service.repository.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
