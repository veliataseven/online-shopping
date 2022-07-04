package com.example.online.shopping.exceptions.AppUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AppUserIsAlreadyExistException extends RuntimeException {
    public AppUserIsAlreadyExistException(String message) {
        super(message);
    }
}
