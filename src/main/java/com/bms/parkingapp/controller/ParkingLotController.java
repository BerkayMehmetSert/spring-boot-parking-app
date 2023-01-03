package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.ParkingLotDto;
import com.bms.parkingapp.dto.request.CreateParkingLotRequest;
import com.bms.parkingapp.dto.request.UpdateParkingLotRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.ParkingLotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lots")
@Slf4j
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping
    public ResponseEntity<Void> createParkingLot(@RequestBody CreateParkingLotRequest request) {
        parkingLotService.createParkingLot(request);

        log.info(ControllerLogMessage.ParkingLot.PARKING_LOT_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParkingLot(@PathVariable String id,
                                                 @RequestBody UpdateParkingLotRequest request) {
        parkingLotService.updateParkingLot(id, request);

        log.info(ControllerLogMessage.ParkingLot.PARKING_LOT_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable String id) {
        parkingLotService.deleteParkingLot(id);

        log.info(ControllerLogMessage.ParkingLot.PARKING_LOT_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLotDto> findParkingLotById(@PathVariable String id) {
        ParkingLotDto parkingLotDto = parkingLotService.findParkingLotById(id);

        log.info(ControllerLogMessage.ParkingLot.PARKING_LOT_FOUND_SUCCESS + id);
        return ResponseEntity.ok(parkingLotDto);
    }

    @GetMapping
    public ResponseEntity<List<ParkingLotDto>> findAllParkingLots() {
        List<ParkingLotDto> parkingLots = parkingLotService.findAllParkingLots();

        log.info(ControllerLogMessage.ParkingLot.PARKING_LOT_LIST_SUCCESS);
        return ResponseEntity.ok(parkingLots);
    }
}
