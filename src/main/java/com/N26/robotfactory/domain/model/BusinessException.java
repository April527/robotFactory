package com.N26.robotfactory.domain.model;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
