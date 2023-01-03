package com.bms.parkingapp.dto;

import java.time.LocalDate;

public record PricingExceptionalDto(
        String id,
        LocalDate date,
        Double morningHoursCost,
        Double midDayHoursCost,
        Double eveningHoursCost,
        Double allDayHoursCost,
        String parkingLotId
){
}
