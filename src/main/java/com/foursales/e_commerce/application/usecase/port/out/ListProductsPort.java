package com.foursales.e_commerce.application.usecase.port.out;

import com.foursales.e_commerce.repository.entity.ProdutoEntity;

import java.util.List;

public interface ListProductsPort {

    List<ProdutoEntity> listProducts();
}
