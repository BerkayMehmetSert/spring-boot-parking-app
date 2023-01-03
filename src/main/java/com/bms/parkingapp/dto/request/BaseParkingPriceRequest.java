package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParkingPriceRequest {
    private Integer dayOfWeek;
    private Double morningHoursCost;
    private Double midDayHoursCost;
    private Double eveningHoursCost;
    private String parkingLotId;
}
