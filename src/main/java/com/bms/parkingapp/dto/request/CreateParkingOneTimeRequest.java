package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateParkingOneTimeRequest extends BaseParkingOneTimeRequest{
    private Boolean payForMinHour;
    private Double bookingForHours;
    private Boolean isPaid;
    private Double basicParkingCost;
    private String parkingLotId;
}
