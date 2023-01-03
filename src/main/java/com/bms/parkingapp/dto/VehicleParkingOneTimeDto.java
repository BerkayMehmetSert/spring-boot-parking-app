package com.bms.parkingapp.dto;

import java.time.LocalDateTime;

public record VehicleParkingOneTimeDto(
        String id,
        LocalDateTime startTime,
        Boolean payForMinHours,
        Double bookingForHours,
        Double basicParkingCost,
        String couponCode,
        Double netCost,
        Boolean isPaid,
        String parkingLotId
) {
}
