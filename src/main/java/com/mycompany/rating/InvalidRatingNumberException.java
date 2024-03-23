package com.mycompany.rating;

public class InvalidRatingNumberException  extends Throwable {
    public InvalidRatingNumberException(String message) {
        super(message);
    }
}