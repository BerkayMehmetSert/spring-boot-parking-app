package com.bms.parkingapp.dto;

import java.time.LocalDate;
import java.util.List;

public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String billingAddress,
        String contactNumber,
        LocalDate registrationDate,
        Boolean isRegularCustomer,
        List<CustomerParkingMonthlyDto> parkingMonthlies,
        List<CustomerPaymentMethodDto> paymentMethods,
        List<CustomerVehicleDto> vehicles
) {
}
