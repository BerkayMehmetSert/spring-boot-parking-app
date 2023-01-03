package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.PaymentMethodDto;
import com.bms.parkingapp.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodDtoConverter {
    public PaymentMethodDto convert(PaymentMethod from) {
        return new PaymentMethodDto(
                from.getId(),
                from.getCardType(),
                from.getCardNumber(),
                from.getExpiryMonth(),
                from.getExpiryYear(),
                from.getSecurityCode(),
                from.getCustomer() != null ? from.getCustomer().getId() : null
        );
    }

    public List<PaymentMethodDto> convert(List<PaymentMethod> from) {
        return from.stream().map(this::convert).toList();
    }
}
