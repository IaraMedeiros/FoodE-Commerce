package com.mycompany.exceptions;

public class RatingNotFoundException extends Throwable {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
