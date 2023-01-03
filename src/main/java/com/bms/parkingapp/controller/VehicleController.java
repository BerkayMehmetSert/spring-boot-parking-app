package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.VehicleDto;
import com.bms.parkingapp.dto.request.CreateVehicleRequest;
import com.bms.parkingapp.dto.request.UpdateVehicleRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Slf4j
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Void> createVehicle(@RequestBody CreateVehicleRequest request) {
        vehicleService.createVehicle(request);

        log.info(ControllerLogMessage.Vehicle.VEHICLE_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable String id,
                                              @RequestBody UpdateVehicleRequest request) {
        vehicleService.updateVehicle(id, request);

        log.info(ControllerLogMessage.Vehicle.VEHICLE_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);

        log.info(ControllerLogMessage.Vehicle.VEHICLE_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findVehicleByVehicleId(@PathVariable String id) {
        VehicleDto vehicle = vehicleService.findVehicleById(id);

        log.info(ControllerLogMessage.Vehicle.VEHICLE_FOUND_SUCCESS + id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> findAllVehicles() {
        List<VehicleDto> vehicles = vehicleService.findAllVehicles();

        log.info(ControllerLogMessage.Vehicle.VEHICLE_LIST_SUCCESS);
        return ResponseEntity.ok(vehicles);
    }
}
