package com.bms.parkingapp.dto;

import java.time.LocalDateTime;

public record ParkingOneTimeDto(
        String id,
        LocalDateTime startTime,
        Boolean payForMinHours,
        Double bookingForHours,
        Double basicParkingCost,
        String offerCode,
        Double netCost,
        Boolean isPaid,
        String parkingLotId,
        String vehicleId
) {
}
