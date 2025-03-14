package com.foursales.e_commerce.controller;

import com.foursales.e_commerce.application.usecase.CreateProductUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record ProdutosController(CreateProductUseCase createProductUseCase) {

    @GetMapping
    public void teste(){
        createProductUseCase.createProduct(null);
    }
}
