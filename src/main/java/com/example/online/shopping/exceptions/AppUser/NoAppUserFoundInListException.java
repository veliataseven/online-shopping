package com.example.online.shopping.exceptions.AppUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NoAppUserFoundInListException extends RuntimeException {
        public NoAppUserFoundInListException(String message) {
            super(message);
        }
    }
