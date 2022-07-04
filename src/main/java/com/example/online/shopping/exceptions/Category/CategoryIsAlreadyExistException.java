package com.example.online.shopping.exceptions.Category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryIsAlreadyExistException extends RuntimeException {
    public CategoryIsAlreadyExistException(String message) {
        super(message);
    }
}
