package com.foursales.e_commerce.exeption;

public class OrderServiceException extends BaseException {
    public OrderServiceException(String message, int statusCode) {
        super(message, statusCode);
    }
    public OrderServiceException(String message, Throwable e,int statusCode) {
        super(message, e,  statusCode);
    }
}
