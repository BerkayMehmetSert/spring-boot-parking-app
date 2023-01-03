package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.CustomerDto;
import com.bms.parkingapp.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDtoConverter {
    private final CustomerParkingMonthlyDtoConverter customerParkingMonthlyDtoConverter;
    private final CustomerPaymentMethodDtoConverter customerPaymentMethodDtoConverter;
    private final CustomerVehicleDtoConverter customerVehicleDtoConverter;

    public CustomerDtoConverter(CustomerParkingMonthlyDtoConverter customerParkingMonthlyDtoConverter,
                                CustomerPaymentMethodDtoConverter customerPaymentMethodDtoConverter,
                                CustomerVehicleDtoConverter customerVehicleDtoConverter) {
        this.customerParkingMonthlyDtoConverter = customerParkingMonthlyDtoConverter;
        this.customerPaymentMethodDtoConverter = customerPaymentMethodDtoConverter;
        this.customerVehicleDtoConverter = customerVehicleDtoConverter;
    }

    public CustomerDto convert(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getBillingAddress(),
                from.getContactNumber(),
                from.getRegistrationDate(),
                from.getIsRegularCustomer(),
                from.getParkingMonthlies() != null ? customerParkingMonthlyDtoConverter.convert(from.getParkingMonthlies()) : null,
                from.getPaymentMethods() != null ? customerPaymentMethodDtoConverter.convert(from.getPaymentMethods()) : null,
                from.getVehicles() != null ? customerVehicleDtoConverter.convert(from.getVehicles()) : null
        );
    }

    public List<CustomerDto> convert(List<Customer> from) {
        return from.stream().map(this::convert).toList();
    }
}
