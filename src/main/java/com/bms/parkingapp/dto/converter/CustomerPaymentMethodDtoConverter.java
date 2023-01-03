package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.CustomerPaymentMethodDto;
import com.bms.parkingapp.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerPaymentMethodDtoConverter {
    public CustomerPaymentMethodDto convert(PaymentMethod from) {
        return new CustomerPaymentMethodDto(
                from.getId(),
                from.getCardType(),
                from.getCardNumber(),
                from.getExpiryMonth(),
                from.getExpiryYear(),
                from.getSecurityCode()
        );
    }

    public List<CustomerPaymentMethodDto> convert(List<PaymentMethod> from) {
        return from.stream().map(this::convert).toList();
    }
}
