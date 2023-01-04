package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePricingExceptionalRequest {
    private LocalDate date;
    private Double morningHoursCost;
    private Double midDayHoursCost;
    private Double eveningHoursCost;
    private String parkingLotId;
}
