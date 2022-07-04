package com.example.online.shopping.exceptions.AppUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppUserGlobalExceptionHandler {

    @ExceptionHandler(AppUserNotFoundException.class)
    public ResponseEntity<Object> handleAppUserNotFound(AppUserNotFoundException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (AppUserNotFoundException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppUserException.class)
    public ResponseEntity<Object> handleAppUserException(AppUserException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (AppUserException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppUserIsAlreadyExistException.class)
    public ResponseEntity<Object> handleAppUserIsAlreadyExistException(
            AppUserIsAlreadyExistException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (AppUserIsAlreadyExistException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoAppUserFoundInListException.class)
    public ResponseEntity<Object> handleNoAppUserFoundInListException(
            NoAppUserFoundInListException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (NoAppUserFoundInListException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
}
