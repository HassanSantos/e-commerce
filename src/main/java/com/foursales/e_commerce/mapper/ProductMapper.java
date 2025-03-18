package com.foursales.e_commerce.mapper;

import com.foursales.e_commerce.dto.ProductDto;
import com.foursales.e_commerce.infrastructure.service.repository.entity.ProdutoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto> entityToModel(List<ProdutoEntity> produtoEntities);
    ProdutoEntity productToProductEntity(ProductDto productDto);
    ProductDto productEntityToProduct(ProdutoEntity product);
}
