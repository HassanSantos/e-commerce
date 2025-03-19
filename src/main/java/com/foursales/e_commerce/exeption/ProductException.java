package com.foursales.e_commerce.exeption;

public class ProductException extends BaseException {
    public ProductException(String message, Exception cause, int statusCode) {
        super(message, cause, statusCode);
    }

    public ProductException(String message, int statusCode) {
        super(message, statusCode);
    }
}
