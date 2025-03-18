package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.comum.ProductMapper;
import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import com.foursales.e_commerce.repository.OrderProductRepository;
import com.foursales.e_commerce.repository.OrderRepository;
import com.foursales.e_commerce.repository.ProductRepository;
import com.foursales.e_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductServiceImpl(ProductRepository productRepository,
                                 UserRepository userRepository,
                                 OrderRepository orderRepository,
                                 OrderProductRepository orderProductRepository,
                                 ProductMapper productMapper) implements ProductService {

    @Override
    public Product createProduct(Product product) {
        var productEntity = productMapper.productToProductEntity(product);

        try {

            productRepository.save(productEntity);

        } catch (Exception e) {
//            TODO: CRIAR EXCEPTION EXCLUSIVA
            new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product updateProduct(Product product) {

        try {
            var productEntity = productMapper.productToProductEntity(product);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void deleteProduct(String id) {

        try {
            if (orderProductRepository.existsByProduct_Id(id)) {
                productRepository.findById(id).ifPresent(produto -> {
                    produto.setAtivo(false);
                    productRepository.save(produto);
                });
            } else {
                productRepository.deleteById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productMapper.entityToModel(productRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product getProductById(String id) {

        return productRepository.findById(id)
                .map(productMapper::productEntityToProduct)
                .orElse(null);
    }
}
