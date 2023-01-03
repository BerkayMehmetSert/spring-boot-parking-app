package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.OfferDto;
import com.bms.parkingapp.model.Offer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferDtoConverter {
    public OfferDto convert(Offer from){
        return new OfferDto(
                from.getId(),
                from.getIssuedOn(),
                from.getValidTill(),
                from.getBookingDateFrom(),
                from.getBookingDateTill(),
                from.getDiscountInPercentage(),
                from.getMaxAmountOffer(),
                from.getDiscountInAmount(),
                from.getOfferCode(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<OfferDto> convert(List<Offer> from){
        return from.stream().map(this::convert).toList();
    }
}
