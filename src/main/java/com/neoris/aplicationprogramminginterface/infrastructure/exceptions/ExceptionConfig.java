package com.neoris.aplicationprogramminginterface.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handle(ResourceNotFoundException e){
        Error error = new Error();
        error.setCode(HttpStatus.NOT_FOUND.name());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Error> handle(BusinessException e){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handle(MethodArgumentTypeMismatchException e){
        Error error = new Error();
        error.setCode(HttpStatus.BAD_REQUEST.name());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
