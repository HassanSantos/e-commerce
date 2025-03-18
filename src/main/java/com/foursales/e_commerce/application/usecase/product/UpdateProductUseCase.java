package com.foursales.e_commerce.application.usecase.product;

import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import org.springframework.stereotype.Service;

@Service
public record UpdateProductUseCase(ProductService productService) {

    public void execute(Product product) {

        productService.updateProduct(product);
    }
}
