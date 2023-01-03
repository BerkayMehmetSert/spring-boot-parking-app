package com.bms.parkingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOfferRequest {
    private LocalDate issuedOn;

    private LocalDate validTill;

    private LocalDate bookingDateFrom;

    private LocalDate bookingDateTill;

    private Double discountInPercentage;

    private Double maxAmountOffer;

    private Double discountInAmount;

    private String parkingLotId;
}
