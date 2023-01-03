package com.bms.parkingapp.dto;

import java.time.LocalDate;

public record OfferDto(
        String id,
        LocalDate issuedOn,
        LocalDate validTill,
        LocalDate bookingDateFrom,
        LocalDate bookingDateTill,
        Double discountInPercentage,
        Double maxAmountOffer,
        Double discountInAmount,
        String offerCode,
        String parkingLotId
) {
}
