package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParkingLotRequest {
    private Integer numberOfBlocks;
    private Boolean isSlotAvailable;
    private String address;
    private String zipCode;
    private Boolean isReentryAllowed;
    private String operatingCompany;
    private Boolean isValetParkingAvailable;
    private Boolean operationalInNight;
    private Double minimumHoursToPay;
    private Boolean isMonthlyPassAllowed;
    private Double monthlyPassCost;
}
