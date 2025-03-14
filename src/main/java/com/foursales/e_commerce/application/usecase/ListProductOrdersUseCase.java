package com.foursales.e_commerce.application.usecase;

import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ListProductOrdersUseCase(ProductService productService) {

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
