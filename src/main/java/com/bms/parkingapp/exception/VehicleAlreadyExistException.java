package com.bms.parkingapp.exception;

public class VehicleAlreadyExistException extends RuntimeException {
    public VehicleAlreadyExistException(String message) {
        super(message);
    }
}
