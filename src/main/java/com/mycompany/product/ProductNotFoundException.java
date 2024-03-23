package com.mycompany.product;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
