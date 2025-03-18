package com.foursales.e_commerce.infrastructure.service;

public class ProductException extends RuntimeException {
    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
