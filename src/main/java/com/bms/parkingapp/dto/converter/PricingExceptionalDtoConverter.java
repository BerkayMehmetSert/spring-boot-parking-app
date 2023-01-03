package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.PricingExceptionalDto;
import com.bms.parkingapp.model.PricingExceptional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricingExceptionalDtoConverter {
    public PricingExceptionalDto convert(PricingExceptional from) {
        return new PricingExceptionalDto(
                from.getId(),
                from.getDate(),
                from.getMorningHoursCost(),
                from.getMidDayHoursCost(),
                from.getEveningHoursCost(),
                from.getAllDayCost(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<PricingExceptionalDto> convert(List<PricingExceptional> from) {
        return from.stream().map(this::convert).toList();
    }
}
