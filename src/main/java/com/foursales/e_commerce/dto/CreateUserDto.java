package com.foursales.e_commerce.dto;

import com.foursales.e_commerce.repository.entity.enums.RoleName;

public record CreateUserDto(
        String email,
        String cpf,
        String password,
        RoleName role
) {
}
