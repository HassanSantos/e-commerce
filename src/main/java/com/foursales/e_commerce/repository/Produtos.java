package com.foursales.e_commerce.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Integer quantidade_estoque;
    private LocalDateTime data_criacao;
    private LocalDateTime data_atualizacao;
}
