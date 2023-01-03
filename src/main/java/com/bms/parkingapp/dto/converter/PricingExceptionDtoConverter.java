package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.PricingExceptionDto;
import com.bms.parkingapp.model.PricingException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricingExceptionDtoConverter {
    public PricingExceptionDto convert(PricingException from) {
        return new PricingExceptionDto(
                from.getId(),
                from.getDate(),
                from.getMorningHoursCost(),
                from.getMidDayHoursCost(),
                from.getEveningHoursCost(),
                from.getAllDayCost(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<PricingExceptionDto> convert(List<PricingException> from) {
        return from.stream().map(this::convert).toList();
    }
}
