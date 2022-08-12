package com.solvd.fooddelivery.exception;

public class NegativePriceValueException extends RuntimeException {

    public NegativePriceValueException() {
        super();
    }

    public NegativePriceValueException(String message) {
        super(message);
    }

    public NegativePriceValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativePriceValueException(Throwable cause) {
        super(cause);
    }
}
