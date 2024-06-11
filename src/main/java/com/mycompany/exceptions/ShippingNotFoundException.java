package com.mycompany.exceptions;

public class ShippingNotFoundException extends Throwable {
    public ShippingNotFoundException(String message) {
        super(message);
    }
}
