package com.neoris.aplicationprogramminginterface.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handle(ResourceNotFoundException e){
        Error error = new Error();
        error.setCode(HttpStatus.NOT_FOUND.name());
        error.setMessage(e.getMessage());
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
