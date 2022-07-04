package com.example.online.shopping.exceptions.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryGlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(
            CategoryNotFoundException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (CategoryNotFoundException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryIsAlreadyExistException.class)
    public ResponseEntity<Object> handleCategoryIsAlreadyExistException(
            CategoryIsAlreadyExistException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (CategoryIsAlreadyExistException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoCategoryFoundInListException.class)
    public ResponseEntity<Object> handleNoCategoryFoundInListException(
            NoCategoryFoundInListException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (NoCategoryFoundInListException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
}
