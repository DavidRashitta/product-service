package com.david.tasks.productservice.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message, String sku) {
        super(message + sku);
    }
}
