package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignVehicleRequest {
    private String vehicleId;
    private String parkingLotId;
    private String offerCode;
}
