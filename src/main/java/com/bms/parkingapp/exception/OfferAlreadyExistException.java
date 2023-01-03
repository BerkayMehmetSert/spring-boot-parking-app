package com.bms.parkingapp.exception;

public class OfferAlreadyExistException extends RuntimeException {
    public OfferAlreadyExistException(String message) {
        super(message);
    }
}
