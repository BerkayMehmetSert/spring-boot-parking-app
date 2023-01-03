package com.bms.parkingapp.exception;

public class ParkingPriceNotFoundException extends RuntimeException {
    public ParkingPriceNotFoundException(String message) {
        super(message);
    }
}
