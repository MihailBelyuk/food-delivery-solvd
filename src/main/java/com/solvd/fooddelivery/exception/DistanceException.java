package com.solvd.fooddelivery.exception;

public class DistanceException extends RuntimeException{

    public DistanceException() {
        super();
    }

    public DistanceException(String message) {
        super(message);
    }

    public DistanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistanceException(Throwable cause) {
        super(cause);
    }
}
