package com.bms.parkingapp.dto;

import java.time.LocalDate;

public record CustomerParkingMonthlyDto(
        String id,
        LocalDate purchaseDate,
        LocalDate startDate,
        Integer durationInDays,
        Double cost,
        String parkingLotId
) {
}
