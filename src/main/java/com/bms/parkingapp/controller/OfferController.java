package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.OfferDto;
import com.bms.parkingapp.dto.request.CreateOfferRequest;
import com.bms.parkingapp.dto.request.UpdateOfferRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
@Slf4j
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody CreateOfferRequest request) {
        offerService.createOffer(request);

        log.info(ControllerLogMessage.Offer.OFFER_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOffer(@PathVariable String id,
                                            @RequestBody UpdateOfferRequest request) {
        offerService.updateOffer(id, request);

        log.info(ControllerLogMessage.Offer.OFFER_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        offerService.deleteOffer(id);

        log.info(ControllerLogMessage.Offer.OFFER_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable String id) {
        OfferDto offer = offerService.findOfferById(id);

        log.info(ControllerLogMessage.Offer.OFFER_FOUND_SUCCESS + id);
        return ResponseEntity.ok(offer);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OfferDto> findOfferByCode(@PathVariable String code) {
        OfferDto offer = offerService.findOfferByCode(code);

        log.info(ControllerLogMessage.Offer.OFFER_FOUND_SUCCESS + code);
        return ResponseEntity.ok(offer);
    }

    @GetMapping
    public ResponseEntity<List<OfferDto>> findALlOffers(){
        List<OfferDto> offers = offerService.findAllOffers();

        log.info(ControllerLogMessage.Offer.OFFER_LIST_SUCCESS);
        return ResponseEntity.ok(offers);
    }
}
