package com.foursales.e_commerce.application.usecase.product;

import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.stereotype.Service;

@Service
public record FindProductUseCase(ProductService productService) {

    public Product exect(String product) {

        return productService.getProductById(product);
    }
}
