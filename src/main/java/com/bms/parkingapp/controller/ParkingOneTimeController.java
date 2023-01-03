package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.ParkingOneTimeDto;
import com.bms.parkingapp.dto.request.AssignVehicleRequest;
import com.bms.parkingapp.dto.request.CreateParkingOneTimeRequest;
import com.bms.parkingapp.dto.request.UpdateParkingOneTimeRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.ParkingOneTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-one-times")
@Slf4j
public class ParkingOneTimeController {
    private final ParkingOneTimeService parkingOneTimeService;

    public ParkingOneTimeController(ParkingOneTimeService parkingOneTimeService) {
        this.parkingOneTimeService = parkingOneTimeService;
    }

    @PostMapping
    public ResponseEntity<Void> createParkingOneTime(@RequestBody CreateParkingOneTimeRequest request) {
        parkingOneTimeService.createParkingOneTime(request);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParkingOneTime(@PathVariable String id, @RequestBody UpdateParkingOneTimeRequest request) {
        parkingOneTimeService.updateParkingOneTime(id, request);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/vehicle")
    public ResponseEntity<Void> assignVehicleToParkingOneTime(@PathVariable String id,
                                                              @RequestParam AssignVehicleRequest request) {
        parkingOneTimeService.assignVehicleToParkingOneTime(id, request);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/vehicle")
    public ResponseEntity<Void> removeVehicleFromParkingOneTime(@PathVariable String id) {
        parkingOneTimeService.removeVehicleFromParkingOneTime(id);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingOneTime(@PathVariable String id) {
        parkingOneTimeService.deleteParkingOneTime(id);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingOneTimeDto> findParkingOneTimeById(@PathVariable String id) {
        ParkingOneTimeDto parkingOneTime = parkingOneTimeService.findParkingOneTimeById(id);

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_FOUND_SUCCESS + id);
        return ResponseEntity.ok(parkingOneTime);
    }

    @GetMapping
    public ResponseEntity<List<ParkingOneTimeDto>> findAllParkingOneTimes() {
        List<ParkingOneTimeDto> parkingOneTimes = parkingOneTimeService.findAllParkingOneTimes();

        log.info(ControllerLogMessage.ParkingOneTime.PARKING_ONE_TIME_LIST_SUCCESS);
        return ResponseEntity.ok(parkingOneTimes);
    }
}
