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
import com.foursales.e_commerce.repository.entity.ProdutoEntity;
import com.foursales.e_commerce.repository.entity.User;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public record ProductServiceImpl(ProductRepository productRepository,
                                 UserRepository userRepository,
                                 OrderRepository orderRepository,
                                 OrderProductRepository orderProductRepository) implements ProductService {

    @Override
    public Product createProduct() {
        var product = ProdutoEntity.builder()
                .name("Notebook ABC")
                .description("Notebook com 16GB de RAM e SSD de 512GB")
                .price(new BigDecimal("5999.99"))
                .category("Informática")
                .stockQuantity(50)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

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
                .product(product) // Associando o Product ao OrderProduct
                .productQuantity(1)
                .build();


        try {
            userRepository.save(user);
            orderRepository.save(orderEntity);
            productRepository.save(product);
            orderProductRepository.save(orderProduct);

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
    public void deleteProduct(UUID id) {

    }

    @Override
    public List<Product> getAllProducts() {
        try {
            var mapper = Mappers.getMapper(ProductMapper.class);

            var lista = orderRepository.findAll();
            return mapper.entityToModel(productRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product getProductById(UUID id) {
        return null;
    }
}
