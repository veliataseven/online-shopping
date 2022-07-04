package com.example.online.shopping.exceptions.PhysicalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPhysicalProductFoundInListException extends RuntimeException {
    public NoPhysicalProductFoundInListException(String message) {
        super(message);
    }
}
