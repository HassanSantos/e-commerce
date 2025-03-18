package com.foursales.e_commerce.security.authentication;

import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationException extends RuntimeException {
    private int statusCode;

    public CustomAuthenticationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

class TokenNotFoundException extends CustomAuthenticationException {
    public TokenNotFoundException(String message) {
        super(message, HttpServletResponse.SC_FORBIDDEN); // HTTP 403
    }
}

class UserNotFoundException extends CustomAuthenticationException {
    public UserNotFoundException(String message) {
        super(message, HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401
    }
}
