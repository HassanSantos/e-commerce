package com.foursales.e_commerce.application.usecase.product;

import com.foursales.e_commerce.domain.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public record DeleteProductUseCase(ProductService productService) {

    public void deleteProduct(String id) {

        productService.deleteProduct(id);
    }
}
