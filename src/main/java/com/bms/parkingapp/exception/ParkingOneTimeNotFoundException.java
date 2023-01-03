package com.bms.parkingapp.exception;

public class ParkingOneTimeNotFoundException extends RuntimeException {
    public ParkingOneTimeNotFoundException(String message) {
        super(message);
    }
}
