package com.mycompany.rating;

public class RatingNotFoundException extends Throwable {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
