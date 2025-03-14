package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.comum.ProductMapper;
import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.domain.service.model.Product;
import com.foursales.e_commerce.repository.ProductRepository;
import com.foursales.e_commerce.repository.entity.ProdutoEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public record ProductServiceImpl(ProductRepository productRepository) implements ProductService {

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
        try {

        productRepository.save(product);
        }catch (Exception e){
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
        var mapper = Mappers.getMapper(ProductMapper.class);
        return mapper.entityToModel(productRepository.findAll());
    }

    @Override
    public Product getProductById(UUID id) {
        return null;
    }
}
