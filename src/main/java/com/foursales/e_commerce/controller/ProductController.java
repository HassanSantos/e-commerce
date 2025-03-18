package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public record ProductController(ProductService productService) {

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
        return ResponseEntity.ok("ok");
    }
}
