package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.VehicleDto;
import com.bms.parkingapp.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleDtoConverter {
    private final VehicleParkingOneTimeDtoConverter vehicleParkingOneTimeDtoConverter;

    public VehicleDtoConverter(VehicleParkingOneTimeDtoConverter vehicleParkingOneTimeDtoConverter) {
        this.vehicleParkingOneTimeDtoConverter = vehicleParkingOneTimeDtoConverter;
    }

    public VehicleDto convert(Vehicle from) {
        return new VehicleDto(
                from.getId(),
                from.getVehicleNumber(),
                from.getCustomer() != null ? from.getCustomer().getId() : null,
                from.getParkingOneTimes() != null ? vehicleParkingOneTimeDtoConverter.convert(from.getParkingOneTimes()) : null
        );
    }

    public List<VehicleDto> convert(List<Vehicle> from) {
        return from.stream().map(this::convert).toList();
    }
}
