package com.foursales.e_commerce.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="pedido")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderEntity {

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