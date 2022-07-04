package com.example.online.shopping.exceptions.AppUser;

public class AppUserException extends RuntimeException {
    public <T> AppUserException(T message) {
        super((String) message);
    }
}
