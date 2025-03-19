package com.foursales.e_commerce.exeption;

public class BaseException extends RuntimeException {
    private int statusCode;

    public BaseException(String message, Throwable throwable, int statusCode) {
        super(message + throwable.getMessage(), throwable);
        this.statusCode = statusCode;
    }

    public BaseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
