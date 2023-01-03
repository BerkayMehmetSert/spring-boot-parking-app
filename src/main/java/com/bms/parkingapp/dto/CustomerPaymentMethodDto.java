package com.bms.parkingapp.dto;

import com.bms.parkingapp.model.CardType;

public record CustomerPaymentMethodDto(
        String id,
        CardType cardType,
        String cardNumber,
        String expiryMonth,
        String expiryYear,
        String securityCode
) {
}
