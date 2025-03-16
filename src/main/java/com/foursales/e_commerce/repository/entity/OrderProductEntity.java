package com.foursales.e_commerce.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pedido_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoEntity product;

    @Column(name = "quantidade_produto")
    private Integer productQuantity;
}
