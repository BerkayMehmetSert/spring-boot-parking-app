package com.bms.parkingapp.exception;

public class ParkingMonthlyAlreadyExistException extends RuntimeException {
    public ParkingMonthlyAlreadyExistException(String message) {
        super(message);
    }
}
