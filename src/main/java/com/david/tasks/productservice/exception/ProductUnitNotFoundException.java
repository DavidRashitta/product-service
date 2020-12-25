package com.david.tasks.productservice.exception;

public class ProductUnitNotFoundException extends RuntimeException {
    public ProductUnitNotFoundException(String firstMessage, String sku, String secondMessage, String unit) {
        super(firstMessage + sku + secondMessage + unit);
    }
}
