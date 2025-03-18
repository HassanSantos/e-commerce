package com.foursales.e_commerce.infrastructure.service;

import com.foursales.e_commerce.exeption.BaseException;

public class ProductException extends BaseException {
    public ProductException(String message, Throwable cause, int statusCode) {
        super(message, cause, statusCode);
    }
}
