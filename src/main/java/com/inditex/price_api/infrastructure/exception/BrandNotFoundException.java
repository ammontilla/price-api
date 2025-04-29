package com.inditex.price_api.infrastructure.exception;

public class BrandNotFoundException extends DomainException {
    public BrandNotFoundException(String message) {
        super(message);
    }
}