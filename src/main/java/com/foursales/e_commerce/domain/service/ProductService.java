package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.domain.service.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    //TODO: ALTERAR
    Product createProduct();
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
    List<Product> getAllProducts();
    Product getProductById(UUID id);
}
