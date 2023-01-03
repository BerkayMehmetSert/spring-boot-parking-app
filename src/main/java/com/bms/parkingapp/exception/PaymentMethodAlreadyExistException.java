package com.bms.parkingapp.exception;

public class PaymentMethodAlreadyExistException extends RuntimeException {
    public PaymentMethodAlreadyExistException(String message) {
        super(message);
    }
}
