package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParkingMonthlyRequest {
    private LocalDate startDate;
    private Integer durationInDays;
    private String parkingLotId;
    private String customerId;
}
