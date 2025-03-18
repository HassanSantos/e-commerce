package com.foursales.e_commerce.domain.service;

import com.foursales.e_commerce.domain.service.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    List<Product> getAllProducts();
    Product getProductById(String id);
}
