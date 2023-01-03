package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.ParkingOneTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingOneTimeRepository extends JpaRepository<ParkingOneTime, String> {
}