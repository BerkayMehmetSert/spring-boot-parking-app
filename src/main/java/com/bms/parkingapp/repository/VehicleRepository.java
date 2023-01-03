package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByVehicleNumber(String vehicleNumber);
}