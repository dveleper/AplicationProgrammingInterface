package com.neoris.aplicationprogramminginterface.infrastructure.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
