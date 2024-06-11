package com.mycompany.exceptions;

public class PaymentNotFoundException extends Throwable {
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
