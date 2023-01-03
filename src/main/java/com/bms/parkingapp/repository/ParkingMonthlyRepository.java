package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.ParkingMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingMonthlyRepository extends JpaRepository<ParkingMonthly, String> {
}