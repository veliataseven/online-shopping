package com.example.online.shopping.exceptions.PhysicalProduct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PhysicalProductGlobalExceptionHandler {

    @ExceptionHandler(PhysicalProductNotFoundException.class)
    public ResponseEntity<Object> handlePhysicalProductNotFoundException(
            PhysicalProductNotFoundException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (PhysicalProductNotFoundException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhysicalProductIsAlreadyExistException.class)
    public ResponseEntity<Object> handlePhysicalProductIsAlreadyExistException(
            PhysicalProductIsAlreadyExistException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (PhysicalProductIsAlreadyExistException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoPhysicalProductFoundInListException.class)
    public ResponseEntity<Object> handleNoPhysicalProductFoundInListException(
            NoPhysicalProductFoundInListException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (NoPhysicalProductFoundInListException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

}
