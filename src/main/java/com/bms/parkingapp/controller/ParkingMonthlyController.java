package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.ParkingMonthlyDto;
import com.bms.parkingapp.dto.request.CreateParkingMonthlyRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.ParkingMonthlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-monthlies")
@Slf4j
public class ParkingMonthlyController {
    private final ParkingMonthlyService parkingMonthlyService;

    public ParkingMonthlyController(ParkingMonthlyService parkingMonthlyService) {
        this.parkingMonthlyService = parkingMonthlyService;
    }

    @PostMapping
    public ResponseEntity<Void> createParkingMonthly(@RequestBody CreateParkingMonthlyRequest request){
        parkingMonthlyService.createParkingMonthly(request);

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/parking-lot")
    public ResponseEntity<Void> updateParkingMonthlyParkingLot(@PathVariable String id,
                                                               @RequestParam String parkingLotId){
        parkingMonthlyService.updateParkingMonthlyParkingLot(id, parkingLotId);

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/duration")
    public ResponseEntity<Void> assignParkingMonthlyToDuration(@PathVariable String id,
                                                               @RequestParam Integer durationInDays){
        parkingMonthlyService.assignParkingMonthlyToDuration(id, durationInDays);

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingMonthly(@PathVariable String id){
        parkingMonthlyService.deleteParkingMonthly(id);

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingMonthlyDto> findParkingMonthlyById(@PathVariable String id){
        ParkingMonthlyDto parkingMonthly = parkingMonthlyService.findParkingMonthlyById(id);

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_FOUND_SUCCESS + id);
        return ResponseEntity.ok(parkingMonthly);
    }

    @GetMapping
    public ResponseEntity<List<ParkingMonthlyDto>> findAllParkingMonthlies(){
        List<ParkingMonthlyDto> parkingMonthlies = parkingMonthlyService.findAllParkingMonthlies();

        log.info(ControllerLogMessage.ParkingMonthly.PARKING_MONTHLY_LIST_SUCCESS);
        return ResponseEntity.ok(parkingMonthlies);
    }
}
