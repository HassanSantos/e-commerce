package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.application.usecase.CreateProductUseCase;
import com.foursales.e_commerce.application.usecase.ListProductOrdersUseCase;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public record ProdutosController(CreateProductUseCase createProductUseCase,
                                 ListProductOrdersUseCase listProductOrdersUseCase) {

    @GetMapping
    public void teste() {
        createProductUseCase.createProduct(null);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(listProductOrdersUseCase.getAllProducts());
    }
}
