package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.PricingExceptionalDto;
import com.bms.parkingapp.dto.converter.PricingExceptionalDtoConverter;
import com.bms.parkingapp.dto.request.CreatePricingExceptionalRequest;
import com.bms.parkingapp.dto.request.UpdatePricingExceptionalRequest;
import com.bms.parkingapp.exception.ParkingLotDoesNotAllowMonthlyPassException;
import com.bms.parkingapp.exception.PricingExceptionalNotFoundException;
import com.bms.parkingapp.helper.Generator;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.ParkingLot;
import com.bms.parkingapp.model.PricingExceptional;
import com.bms.parkingapp.repository.PricingExceptionalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PricingExceptionalService {
    private final PricingExceptionalRepository pricingExceptionalRepository;
    private final ParkingLotService parkingLotService;
    private final PricingExceptionalDtoConverter converter;

    public PricingExceptionalService(PricingExceptionalRepository pricingExceptionalRepository,
                                     ParkingLotService parkingLotService,
                                     PricingExceptionalDtoConverter converter) {
        this.pricingExceptionalRepository = pricingExceptionalRepository;
        this.parkingLotService = parkingLotService;
        this.converter = converter;
    }

    public void createPricingExceptional(CreatePricingExceptionalRequest request) {
        PricingExceptional pricingExceptional = new PricingExceptional();
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId());

        if (Boolean.FALSE.equals(parkingLot.getIsMonthlyPassAllowed())) {
            log.error(BusinessLogMessage.PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
            throw new ParkingLotDoesNotAllowMonthlyPassException(BusinessMessage
                    .PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
        }

        pricingExceptional.setDate(request.getDate());
        pricingExceptional.setMorningHoursCost(request.getMorningHoursCost());
        pricingExceptional.setMidDayHoursCost(request.getMidDayHoursCost());
        pricingExceptional.setEveningHoursCost(request.getEveningHoursCost());
        pricingExceptional.setAllDayCost(Generator.calculateAllDayCost(request.getMorningHoursCost(),
                request.getMidDayHoursCost(), request.getEveningHoursCost()));
        pricingExceptional.setParkingLot(parkingLot);

        pricingExceptionalRepository.save(pricingExceptional);
        log.info(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_SAVE_SUCCESS);
    }

    public void updatePricingExceptional(final String id, UpdatePricingExceptionalRequest request) {
        PricingExceptional pricingExceptional = findPricingExceptionalByPricingExceptionalId(id);
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId());

        if (Boolean.FALSE.equals(parkingLot.getIsMonthlyPassAllowed())) {
            log.error(BusinessLogMessage.PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
            throw new ParkingLotDoesNotAllowMonthlyPassException(BusinessMessage
                    .PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
        }

        pricingExceptional.setDate(request.getDate());
        pricingExceptional.setMorningHoursCost(request.getMorningHoursCost());
        pricingExceptional.setMidDayHoursCost(request.getMidDayHoursCost());
        pricingExceptional.setEveningHoursCost(request.getEveningHoursCost());
        pricingExceptional.setAllDayCost(Generator.calculateAllDayCost(request.getMorningHoursCost(),
                request.getMidDayHoursCost(), request.getEveningHoursCost()));
        pricingExceptional.setParkingLot(parkingLot);

        pricingExceptionalRepository.save(pricingExceptional);
        log.info(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_UPDATE_SUCCESS + id);
    }

    public void deletePricingExceptional(final String id) {
        PricingExceptional pricingExceptional = findPricingExceptionalByPricingExceptionalId(id);

        pricingExceptionalRepository.delete(pricingExceptional);
        log.info(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_DELETE_SUCCESS + id);
    }

    public PricingExceptionalDto findPricingExceptionalById(final String id){
        PricingExceptional pricingExceptional = findPricingExceptionalByPricingExceptionalId(id);

        log.info(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_FOUND_SUCCESS + id);
        return converter.convert(pricingExceptional);
    }

    public List<PricingExceptionalDto> findAllPricingExceptional(){
        List<PricingExceptional> pricingExceptionalList = pricingExceptionalRepository.findAll();

        if (pricingExceptionalList.isEmpty()) {
            log.error(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_LIST_EMPTY);
            throw new PricingExceptionalNotFoundException(BusinessMessage.PricingExceptional.PRICING_EXCEPTION_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_LIST_SUCCESS);
        return converter.convert(pricingExceptionalList);
    }

    protected PricingExceptional findPricingExceptionalByPricingExceptionalId(final String id) {
        return pricingExceptionalRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.PricingExceptional.PRICING_EXCEPTION_NOT_FOUND + id);
                    throw new PricingExceptionalNotFoundException(BusinessMessage
                            .PricingExceptional.PRICING_EXCEPTION_NOT_FOUND);
                });
    }
}
