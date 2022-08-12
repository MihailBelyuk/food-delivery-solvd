package com.solvd.fooddelivery.exception;

public class NegativeQuantityException extends RuntimeException {

    public NegativeQuantityException() {
        super();
    }

    public NegativeQuantityException(String message) {
        super(message);
    }

    public NegativeQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeQuantityException(Throwable cause) {
        super(cause);
    }
}

