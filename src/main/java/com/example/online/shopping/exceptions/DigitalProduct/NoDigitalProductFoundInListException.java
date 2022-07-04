package com.example.online.shopping.exceptions.DigitalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoDigitalProductFoundInListException extends RuntimeException {
    public NoDigitalProductFoundInListException(String message) {
        super(message);
    }
}
