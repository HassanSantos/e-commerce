package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.domain.service.ProductService;
import com.foursales.e_commerce.dto.ProductDto;
import com.foursales.e_commerce.exeption.ProductException;
import com.foursales.e_commerce.infrastructure.service.repository.OrderProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.OrderRepository;
import com.foursales.e_commerce.infrastructure.service.repository.ProductRepository;
import com.foursales.e_commerce.infrastructure.service.repository.UserRepository;
import com.foursales.e_commerce.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ProductServiceImpl(ProductRepository productRepository,
                                 UserRepository userRepository,
                                 OrderRepository orderRepository,
                                 OrderProductRepository orderProductRepository,
                                 ProductMapper productMapper) implements ProductService {

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        var productEntity = productMapper.productToProductEntity(productDto);

        try {

            productRepository.save(productEntity);

        } catch (Exception e) {
//            TODO: CRIAR EXCEPTION EXCLUSIVA
            throw new ProductException("error when save product", e, 500);
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {

        try {
            var productEntity = productMapper.productToProductEntity(productDto);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new ProductException("Erro ao atualizar produto", e, 500);
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
            throw new ProductException("Erro ao deletar produto", e, 500);
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            return productMapper.entityToModel(productRepository.findAll());
        } catch (Exception e) {
            throw new ProductException("Erro ao retornar lista de produtos cadastrados", e, 500);
        }

    }

    @Override
    public ProductDto getProductById(String id) {

        return productRepository.findById(id)
                .map(productMapper::productEntityToProduct)
                .orElseThrow(() -> new ProductException("Erro ao retornar lista de produtos cadastrados", 500));
    }
}
