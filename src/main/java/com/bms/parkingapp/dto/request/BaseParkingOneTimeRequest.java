package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParkingOneTimeRequest {
    private Boolean payForMinHour;
    private Double bookingForHours;
    private Boolean isPaid;
    private Double basicParkingCost;
    private String parkingLotId;
}
