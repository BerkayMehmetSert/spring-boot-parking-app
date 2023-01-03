package com.bms.parkingapp.exception;

public class ParkingLotDoesNotAllowMonthlyPassException extends RuntimeException {
    public ParkingLotDoesNotAllowMonthlyPassException(String message) {
        super(message);
    }
}
