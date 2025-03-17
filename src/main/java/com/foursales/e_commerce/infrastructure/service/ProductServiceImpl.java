package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.comum.ProductMapper;
import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import com.foursales.e_commerce.repository.OrderProductRepository;
import com.foursales.e_commerce.repository.OrderRepository;
import com.foursales.e_commerce.repository.ProductRepository;
import com.foursales.e_commerce.repository.UserRepository;
import com.foursales.e_commerce.repository.entity.OrderEntity;
import com.foursales.e_commerce.repository.entity.OrderProductEntity;
import com.foursales.e_commerce.repository.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    public Product updateProduct(UUID id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        try {
            productRepository.deleteById(id);

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
        return productMapper.productEntityToProduct(productRepository.findById(id).get());
    }

    public Product creatdeProduct(Product product) {
        var productEntity = productMapper.productToProductEntity(product);

        User user = User.builder()
                .name("João Silva")
                .cpf("12345678901")
                .password("senha123")
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        OrderEntity orderEntity = OrderEntity.builder()
                .status("PENDENTE")
                .value(new BigDecimal("4500.00"))
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .user(user) // Associando o User ao Order
                .build();


        OrderProductEntity orderProduct = OrderProductEntity.builder()
                .orderEntity(orderEntity) // Associando o Order ao OrderProduct
                .product(productEntity) // Associando o Product ao OrderProduct
                .productQuantity(1)
                .build();


        try {
            userRepository.save(user);
            orderRepository.save(orderEntity);
            productRepository.save(productEntity);
            orderProductRepository.save(orderProduct);

        } catch (Exception e) {
//            TODO: CRIAR EXCEPTION EXCLUSIVA
            new RuntimeException(e);
        }
        return null;
    }
}
