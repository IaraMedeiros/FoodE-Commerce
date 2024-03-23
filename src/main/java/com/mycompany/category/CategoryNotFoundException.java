package com.mycompany.category;

public class CategoryNotFoundException extends Throwable {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
