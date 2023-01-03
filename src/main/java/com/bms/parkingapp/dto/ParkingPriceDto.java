package com.bms.parkingapp.dto;

public record ParkingPriceDto(
        String id,
        Integer dayOfWeek,
        Double morningHoursCost,
        Double midDayHoursCost,
        Double eveningHoursCost,
        Double allDayHoursCost,
        String parkingLotId
) {
}
