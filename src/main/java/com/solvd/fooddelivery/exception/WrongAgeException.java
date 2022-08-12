package com.solvd.fooddelivery.exception;

public class WrongAgeException extends Exception {

    public WrongAgeException() {
        super();
    }

    public WrongAgeException(String message) {
        super(message);
    }

    public WrongAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAgeException(Throwable cause) {
        super(cause);
    }
}
