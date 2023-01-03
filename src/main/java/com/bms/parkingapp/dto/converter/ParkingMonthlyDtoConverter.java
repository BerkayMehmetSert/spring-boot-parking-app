package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.ParkingMonthlyDto;
import com.bms.parkingapp.model.ParkingMonthly;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingMonthlyDtoConverter {
    public ParkingMonthlyDto convert(ParkingMonthly from) {
        return new ParkingMonthlyDto(
                from.getId(),
                from.getPurchaseDate(),
                from.getStartDate(),
                from.getDurationInDays(),
                from.getCost(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null,
                from.getCustomer() != null ? from.getCustomer().getId() : null
        );
    }

    public List<ParkingMonthlyDto> convert(List<ParkingMonthly> from) {
        return from.stream().map(this::convert).toList();
    }
}
