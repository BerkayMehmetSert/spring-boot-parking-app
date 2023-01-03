package com.bms.parkingapp.dto;

import java.time.LocalDate;

public record ParkingMonthlyDto(
        String id,
        LocalDate purchaseDate,
        LocalDate startDate,
        Integer durationInDays,
        Double cost,
        String parkingLotId,
        String customerId
) {
}
