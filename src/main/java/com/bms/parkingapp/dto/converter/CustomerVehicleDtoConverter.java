package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.CustomerVehicleDto;
import com.bms.parkingapp.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerVehicleDtoConverter {
    public CustomerVehicleDto convert(Vehicle from) {
        return new CustomerVehicleDto(
                from.getId(),
                from.getVehicleNumber()
        );
    }

    public List<CustomerVehicleDto> convert(List<Vehicle> from) {
        return from.stream().map(this::convert).toList();
    }
}
