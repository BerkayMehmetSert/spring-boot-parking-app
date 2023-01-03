package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.PricingExceptionalDto;
import com.bms.parkingapp.dto.request.CreatePricingExceptionalRequest;
import com.bms.parkingapp.dto.request.UpdatePricingExceptionalRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.PricingExceptionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pricing-exceptional")
@Slf4j
public class PricingExceptionalController {
    private final PricingExceptionalService pricingExceptionalService;

    public PricingExceptionalController(PricingExceptionalService pricingExceptionalService) {
        this.pricingExceptionalService = pricingExceptionalService;
    }

    @PostMapping
    public ResponseEntity<Void> createPricingExceptional(@RequestBody CreatePricingExceptionalRequest request) {
        pricingExceptionalService.createPricingExceptional(request);

        log.info(ControllerLogMessage.PricingExceptional.PRICING_EXCEPTION_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePricingExceptional(@PathVariable String id,
                                                         @RequestBody UpdatePricingExceptionalRequest request) {
        pricingExceptionalService.updatePricingExceptional(id, request);

        log.info(ControllerLogMessage.PricingExceptional.PRICING_EXCEPTION_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricingExceptional(@PathVariable String id) {
        pricingExceptionalService.deletePricingExceptional(id);

        log.info(ControllerLogMessage.PricingExceptional.PRICING_EXCEPTION_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingExceptionalDto> findPricingExceptionalById(@PathVariable String id){
        PricingExceptionalDto pricingExceptional = pricingExceptionalService.findPricingExceptionalById(id);

        log.info(ControllerLogMessage.PricingExceptional.PRICING_EXCEPTION_FOUND_SUCCESS + id);
        return ResponseEntity.ok(pricingExceptional);
    }

    @GetMapping
    public ResponseEntity<List<PricingExceptionalDto>> findAllPricingExceptional(){
        List<PricingExceptionalDto> pricingExceptionalList = pricingExceptionalService.findAllPricingExceptional();

        log.info(ControllerLogMessage.PricingExceptional.PRICING_EXCEPTION_LIST_SUCCESS);
        return ResponseEntity.ok(pricingExceptionalList);
    }
}
