package com.foursales.e_commerce.comum;

import com.foursales.e_commerce.domain.service.model.Product;
import com.foursales.e_commerce.repository.entity.ProdutoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<Product> entityToModel(List<ProdutoEntity> produtoEntities);
    ProdutoEntity productToProductEntity(Product product);
    Product productEntityToProduct(ProdutoEntity product);
}
