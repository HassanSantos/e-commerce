package com.foursales.e_commerce.infrastructure.service.repository.entity;

import com.foursales.e_commerce.infrastructure.service.repository.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

}
