package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.ParkingPriceDto;
import com.bms.parkingapp.model.ParkingPrice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingPriceDtoConverter {
    public ParkingPriceDto convert(ParkingPrice from) {
        return new ParkingPriceDto(
                from.getId(),
                from.getDayOfWeek(),
                from.getMorningHoursCost(),
                from.getMidDayHoursCost(),
                from.getEveningHoursCost(),
                from.getAllDayCost(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<ParkingPriceDto> convert(List<ParkingPrice> from) {
        return from.stream().map(this::convert).toList();
    }
}
