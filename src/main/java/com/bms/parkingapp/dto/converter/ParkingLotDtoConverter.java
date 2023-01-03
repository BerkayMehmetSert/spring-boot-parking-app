package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.ParkingLotDto;
import com.bms.parkingapp.model.ParkingLot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLotDtoConverter {
    public ParkingLotDto convert(ParkingLot from) {
        return new ParkingLotDto(
                from.getId(),
                from.getNumberOfBlocks(),
                from.getIsSlotAvailable(),
                from.getAddress(),
                from.getZipCode(),
                from.getIsReentryAllowed(),
                from.getOperatingCompany(),
                from.getIsValetParkingAvailable(),
                from.getOperationalInNight(),
                from.getMinimumHoursToPay(),
                from.getIsMonthlyPassAllowed(),
                from.getMonthlyPassCost()
        );
    }

    public List<ParkingLotDto> convert(List<ParkingLot> from) {
        return from.stream().map(this::convert).toList();
    }
}
