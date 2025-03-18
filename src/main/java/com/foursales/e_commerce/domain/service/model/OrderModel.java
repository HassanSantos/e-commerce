package com.foursales.e_commerce.domain.service.model;

import com.foursales.e_commerce.infrastructure.service.repository.entity.OrderProductEntity;
import com.foursales.e_commerce.infrastructure.service.repository.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "status")
    private String status;

    @Column(name = "valor")
    private BigDecimal value;

    @Column(name = "data_criacao")
    private LocalDateTime creationDate;

    @Column(name = "data_atualizacao")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "orderEntity",fetch = FetchType.EAGER)
    private List<OrderProductEntity> orderProducts;
}
