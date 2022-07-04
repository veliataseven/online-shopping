package com.example.online.shopping.exceptions.Category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoCategoryFoundInListException extends RuntimeException {
    public NoCategoryFoundInListException(String message) {
        super(message);
    }
}
