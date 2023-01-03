package com.bms.parkingapp.dto.converter;

import com.bms.parkingapp.dto.CustomerParkingMonthlyDto;
import com.bms.parkingapp.model.ParkingMonthly;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerParkingMonthlyDtoConverter {
    public CustomerParkingMonthlyDto convert(ParkingMonthly from){
        return new CustomerParkingMonthlyDto(
                from.getId(),
                from.getPurchaseDate(),
                from.getStartDate(),
                from.getDurationInDays(),
                from.getCost(),
                from.getParkingLot() != null ? from.getParkingLot().getId() : null
        );
    }

    public List<CustomerParkingMonthlyDto> convert(List<ParkingMonthly> from){
        return from.stream().map(this::convert).toList();
    }
}
