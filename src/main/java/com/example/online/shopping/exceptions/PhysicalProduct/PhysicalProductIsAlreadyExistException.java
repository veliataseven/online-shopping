package com.example.online.shopping.exceptions.PhysicalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PhysicalProductIsAlreadyExistException extends RuntimeException {
    public PhysicalProductIsAlreadyExistException(String message) {
        super(message);
    }
}
