package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(String id);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(String id);
}
