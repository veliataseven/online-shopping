package com.example.online.shopping.exceptions.DigitalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DigitalProductGlobalExceptionHandler {

    @ExceptionHandler(DigitalProductNotFoundException.class)
    public ResponseEntity<Object> handleDigitalProductNotFoundException(
            DigitalProductNotFoundException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (DigitalProductNotFoundException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DigitalProductIsAlreadyExistException.class)
    public ResponseEntity<Object> handleDigitalProductIsAlreadyExistException(
            DigitalProductIsAlreadyExistException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (DigitalProductIsAlreadyExistException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoDigitalProductFoundInListException.class)
    public ResponseEntity<Object> handleNoDigitalProductFoundInListException(
            NoDigitalProductFoundInListException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (NoDigitalProductFoundInListException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

}
