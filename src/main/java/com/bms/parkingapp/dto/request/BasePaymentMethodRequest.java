package com.bms.parkingapp.dto.request;

import com.bms.parkingapp.model.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePaymentMethodRequest {
    private CardType cardType;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;
    private String customerId;
}
