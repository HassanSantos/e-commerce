package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.application.usecase.product.*;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public record ProductController(CreateProductUseCase createProductUseCase,
                                ListProductOrdersUseCase listProductOrdersUseCase,
                                DeleteProductUseCase deleteProductUseCase,
                                FindProductUseCase findProductUseCase,
                                UpdateProductUseCase updateProductUseCase) {

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        createProductUseCase.createProduct(product);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(listProductOrdersUseCase.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable String id) {
        return ResponseEntity.ok(findProductUseCase.exect(id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Product product) {
        updateProductUseCase.execute(product);
        return ResponseEntity.ok("ok");
    }
}
