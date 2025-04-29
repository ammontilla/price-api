package com.inditex.price_api.infrastructure.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}