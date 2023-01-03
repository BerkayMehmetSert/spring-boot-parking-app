package com.bms.parkingapp.exception;

public class ParkingPriceAlreadyExistException extends RuntimeException {
    public ParkingPriceAlreadyExistException(String message) {
        super(message);
    }
}
