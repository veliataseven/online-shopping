package com.example.online.shopping.exceptions.DigitalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DigitalProductIsAlreadyExistException extends RuntimeException {
    public DigitalProductIsAlreadyExistException(String message) {
        super(message);
    }
}
