package com.foursales.e_commerce.repository.entity;

import com.foursales.e_commerce.domain.service.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "pedido_produto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private ProdutoEntity product;

    @Column(name = "quantidade_produto")
    private Integer productQuantity;
}
