package edu.tsystems.javaschool.logapp.api.exception;

public abstract class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String message) {
        super(message);
    }
}