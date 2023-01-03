package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.ParkingPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPriceRepository extends JpaRepository<ParkingPrice, String> {
}