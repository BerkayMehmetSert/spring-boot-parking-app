package com.bms.parkingapp.dto;

import java.util.List;

public record VehicleDto(
        String id,
        String vehicleNumber,
        String customerId,
        List<VehicleParkingOneTimeDto> parkingOneTimes
) {
}
