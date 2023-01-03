package com.bms.parkingapp.dto;

public record ParkingLotDto(
        String id,
        Integer numberOfBlocks,
        Boolean isSlotAvailable,
        String address,
        String zipCode,
        Boolean isReentryAllowed,
        String operatingCompany,
        Boolean isValetParkingAvailable,
        Boolean operationalInNight,
        Double minimumHoursToPay,
        Boolean isMonthlyPassAllowed,
        Double monthlyPassCost
) {
}
