package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.OfferDto;
import com.bms.parkingapp.dto.converter.OfferDtoConverter;
import com.bms.parkingapp.dto.request.CreateOfferRequest;
import com.bms.parkingapp.dto.request.UpdateOfferRequest;
import com.bms.parkingapp.exception.OfferNotFoundException;
import com.bms.parkingapp.helper.Generator;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.Offer;
import com.bms.parkingapp.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OfferService {
    private final OfferRepository offerRepository;
    private final ParkingLotService parkingLotService;
    private final OfferDtoConverter converter;

    public OfferService(OfferRepository offerRepository,
                        ParkingLotService parkingLotService,
                        OfferDtoConverter converter) {
        this.offerRepository = offerRepository;
        this.parkingLotService = parkingLotService;
        this.converter = converter;
    }

    public void createOffer(CreateOfferRequest request) {
        Offer offer = new Offer();

        offer.setIssuedOn(request.getIssuedOn());
        offer.setValidTill(request.getValidTill());
        offer.setBookingDateFrom(request.getBookingDateFrom());
        offer.setBookingDateTill(request.getBookingDateTill());
        offer.setDiscountInPercentage(request.getDiscountInPercentage());
        offer.setMaxAmountOffer(request.getMaxAmountOffer());
        offer.setDiscountInAmount(request.getDiscountInAmount());
        offer.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));
        offer.setOfferCode(Generator.generateOfferCode());

        offerRepository.save(offer);
        log.info(BusinessLogMessage.Offer.OFFER_SAVE_SUCCESS);
    }

    public void updateOffer(final String id, UpdateOfferRequest request) {
        Offer offer = findOfferByOfferId(id);

        offer.setIssuedOn(request.getIssuedOn());
        offer.setValidTill(request.getValidTill());
        offer.setBookingDateFrom(request.getBookingDateFrom());
        offer.setBookingDateTill(request.getBookingDateTill());
        offer.setDiscountInPercentage(request.getDiscountInPercentage());
        offer.setMaxAmountOffer(request.getMaxAmountOffer());
        offer.setDiscountInAmount(request.getDiscountInAmount());
        offer.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));

        offerRepository.save(offer);
        log.info(BusinessLogMessage.Offer.OFFER_UPDATE_SUCCESS + id);
    }

    public void deleteOffer(final String id) {
        Offer offer = findOfferByOfferId(id);

        offerRepository.delete(offer);
        log.info(BusinessLogMessage.Offer.OFFER_DELETE_SUCCESS + id);
    }

    public OfferDto findOfferById(final String id) {
        Offer offer = findOfferByOfferId(id);

        log.info(BusinessLogMessage.Offer.OFFER_FOUND_SUCCESS + id);
        return converter.convert(offer);
    }

    public OfferDto findOfferByCode(final String offerCode) {
        Offer offer = findOfferByOfferCode(offerCode);

        log.info(BusinessLogMessage.Offer.OFFER_FOUND_SUCCESS + offerCode);
        return converter.convert(offer);
    }

    public List<OfferDto> findAllOffers() {
        List<Offer> offers = offerRepository.findAll();

        if (offers.isEmpty()) {
            log.error(BusinessLogMessage.Offer.OFFER_LIST_EMPTY);
            throw new OfferNotFoundException(BusinessMessage.Offer.OFFER_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Offer.OFFER_LIST_SUCCESS);
        return converter.convert(offers);
    }

    protected Offer findOfferByOfferId(final String id) {
        return offerRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.Offer.OFFER_NOT_FOUND + id);
            throw new OfferNotFoundException(BusinessMessage.Offer.OFFER_NOT_FOUND);
        });
    }

    protected Offer findOfferByOfferCode(final String offerCode) {
        return offerRepository.findByOfferCode(offerCode).orElseThrow(() -> {
            log.error(BusinessLogMessage.Offer.OFFER_NOT_FOUND + offerCode);
            throw new OfferNotFoundException(BusinessMessage.Offer.OFFER_NOT_FOUND);
        });
    }
}
