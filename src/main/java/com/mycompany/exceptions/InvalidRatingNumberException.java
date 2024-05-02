package com.mycompany.exceptions;

public class InvalidRatingNumberException  extends Throwable {
    public InvalidRatingNumberException(String message) {
        super(message);
    }
}