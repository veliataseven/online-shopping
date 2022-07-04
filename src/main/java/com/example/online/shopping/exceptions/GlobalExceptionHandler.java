package com.example.online.shopping.exceptions;

import com.example.online.shopping.exceptions.AppUser.AppUserException;
import com.example.online.shopping.exceptions.AppUser.AppUserIsAlreadyExistException;
import com.example.online.shopping.exceptions.AppUser.AppUserNotFoundException;
import com.example.online.shopping.exceptions.AppUser.NoAppUserFoundInListException;
import com.example.online.shopping.exceptions.Category.CategoryIsAlreadyExistException;
import com.example.online.shopping.exceptions.Category.CategoryNotFoundException;
import com.example.online.shopping.exceptions.Category.NoCategoryFoundInListException;
import com.example.online.shopping.exceptions.DigitalProduct.DigitalProductIsAlreadyExistException;
import com.example.online.shopping.exceptions.DigitalProduct.DigitalProductNotFoundException;
import com.example.online.shopping.exceptions.DigitalProduct.NoDigitalProductFoundInListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (MethodArgumentTypeMismatchException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException exception)
            throws IOException {

        Map<String, String> error = new HashMap<>();
        String fieldName = "Error message (ConstraintViolationException) : ";
        String errorMessage = exception.getMessage();
        error.put(fieldName, errorMessage);

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}
