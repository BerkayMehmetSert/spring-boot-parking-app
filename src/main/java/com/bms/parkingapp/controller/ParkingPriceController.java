package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.ParkingPriceDto;
import com.bms.parkingapp.dto.request.CreateParkingPriceRequest;
import com.bms.parkingapp.dto.request.UpdateParkingPriceRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.ParkingPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-prices")
@Slf4j
public class ParkingPriceController {
    private final ParkingPriceService parkingPriceService;

    public ParkingPriceController(ParkingPriceService parkingPriceService) {
        this.parkingPriceService = parkingPriceService;
    }

    @PostMapping
    public ResponseEntity<Void> createParkingPrice(@RequestBody CreateParkingPriceRequest request){
        parkingPriceService.createParkingPrice(request);

        log.info(ControllerLogMessage.ParkingPrice.PARKING_PRICE_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParkingPrice(@PathVariable String id,
                                                   @RequestBody UpdateParkingPriceRequest request){
        parkingPriceService.updateParkingPrice(id, request);

        log.info(ControllerLogMessage.ParkingPrice.PARKING_PRICE_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingPrice(@PathVariable String id){
        parkingPriceService.deleteParkingPrice(id);

        log.info(ControllerLogMessage.ParkingPrice.PARKING_PRICE_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingPriceDto> findParkingPriceById(@PathVariable String id){
        ParkingPriceDto parkingPrice = parkingPriceService.findParkingPriceById(id);

        log.info(ControllerLogMessage.ParkingPrice.PARKING_PRICE_FOUND_SUCCESS + id);
        return ResponseEntity.ok(parkingPrice);
    }

    @GetMapping
    public ResponseEntity<List<ParkingPriceDto>> findAllParkingPrices(){
        List<ParkingPriceDto> parkingPrices = parkingPriceService.findAllParkingPrices();

        log.info(ControllerLogMessage.ParkingPrice.PARKING_PRICE_LIST_SUCCESS);
        return ResponseEntity.ok(parkingPrices);
    }
}
