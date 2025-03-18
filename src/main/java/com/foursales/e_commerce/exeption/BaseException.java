package com.foursales.e_commerce.exeption;

public class BaseException extends RuntimeException {
    private int statusCode;

    public BaseException(String message, Throwable throwable, int statusCode) {
        super(message, throwable);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
