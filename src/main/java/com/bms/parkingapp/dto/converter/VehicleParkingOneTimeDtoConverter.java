package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.VehicleParkingOneTimeDto;
import com.bms.parkingapp.model.ParkingOneTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleParkingOneTimeDtoConverter {
    public VehicleParkingOneTimeDto convert(ParkingOneTime from) {
        return new VehicleParkingOneTimeDto(
                from.getId(),
                from.getStartTime(),
                from.getPayForMinHour(),
                from.getBookingForHours(),
                from.getBasicParkingCost(),
                from.getCouponCode(),
                from.getNetCost(),
                from.getIsPaid(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<VehicleParkingOneTimeDto> convert(List<ParkingOneTime> from) {
        return from.stream().map(this::convert).toList();
    }
}
