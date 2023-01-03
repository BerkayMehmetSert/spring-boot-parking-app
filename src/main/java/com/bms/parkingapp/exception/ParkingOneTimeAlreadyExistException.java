package com.bms.parkingapp.exception;

public class ParkingOneTimeAlreadyExistException extends RuntimeException {
    public ParkingOneTimeAlreadyExistException(String message) {
        super(message);
    }
}
