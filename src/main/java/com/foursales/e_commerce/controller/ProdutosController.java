package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.application.usecase.product.CreateProductUseCase;
import com.foursales.e_commerce.application.usecase.product.DeleteProductUseCase;
import com.foursales.e_commerce.application.usecase.product.FindProductUseCase;
import com.foursales.e_commerce.application.usecase.product.ListProductOrdersUseCase;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public record ProdutosController(CreateProductUseCase createProductUseCase,
                                 ListProductOrdersUseCase listProductOrdersUseCase,
                                 DeleteProductUseCase deleteProductUseCase,
                                 FindProductUseCase findProductUseCase) {

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
}
