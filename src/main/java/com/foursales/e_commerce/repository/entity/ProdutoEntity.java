package com.foursales.e_commerce.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "produtos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
//TODO: Mudar nome da entidade
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "nome")
    private String name;
    @Column(name = "descricao")
    private String description;
    @Column(name = "preco")
    private BigDecimal price;
    @Column(name = "categoria")
    private String category;
    @Column(name = "quantidade_Estoque")
    private Integer stockQuantity;
    @Column(name = "data_criacao")
    private LocalDateTime createdAt;
    @Column(name = "data_atualizacao")
    private LocalDateTime updatedAt;
    private Boolean ativo;
}
