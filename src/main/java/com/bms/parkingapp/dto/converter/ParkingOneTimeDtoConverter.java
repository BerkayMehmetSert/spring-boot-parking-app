package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.ParkingOneTimeDto;
import com.bms.parkingapp.model.ParkingOneTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingOneTimeDtoConverter {
    public ParkingOneTimeDto convert(ParkingOneTime from) {
        return new ParkingOneTimeDto(
                from.getId(),
                from.getStartTime(),
                from.getPayForMinHour(),
                from.getBookingForHours(),
                from.getBasicParkingCost(),
                from.getCouponCode(),
                from.getNetCost(),
                from.getIsPaid(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null,
                from.getVehicle() != null ? from.getVehicle().getId() : null
        );
    }

    public List<ParkingOneTimeDto> convert(List<ParkingOneTime> from) {
        return from.stream().map(this::convert).toList();
    }
}
