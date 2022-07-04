package com.example.online.shopping.exceptions.Product;

import com.example.online.shopping.exceptions.PhysicalProduct.PhysicalProductIsAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductGlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFoundException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (ProductNotFoundException) : ";
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

    @ExceptionHandler(NoProductFoundInListException.class)
    public ResponseEntity<Object> handleNoProductFoundInListException(
            NoProductFoundInListException exception) throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (NoProductFoundInListException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

}
