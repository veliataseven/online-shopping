package com.example.online.shopping.exceptions.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductFoundInListException extends RuntimeException {
    public NoProductFoundInListException(String message) {
        super(message);
    }
}
