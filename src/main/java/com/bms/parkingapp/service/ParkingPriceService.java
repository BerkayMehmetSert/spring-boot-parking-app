package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.ParkingPriceDto;
import com.bms.parkingapp.dto.converter.ParkingPriceDtoConverter;
import com.bms.parkingapp.dto.request.CreateParkingPriceRequest;
import com.bms.parkingapp.dto.request.UpdateParkingPriceRequest;
import com.bms.parkingapp.exception.ParkingLotDoesNotAllowMonthlyPassException;
import com.bms.parkingapp.exception.ParkingPriceNotFoundException;
import com.bms.parkingapp.helper.Generator;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.ParkingLot;
import com.bms.parkingapp.model.ParkingPrice;
import com.bms.parkingapp.repository.ParkingPriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingPriceService {
    private final ParkingPriceRepository parkingPriceRepository;
    private final ParkingLotService parkingLotService;
    private final ParkingPriceDtoConverter converter;

    public ParkingPriceService(ParkingPriceRepository parkingPriceRepository,
                               ParkingLotService parkingLotService,
                               ParkingPriceDtoConverter converter) {
        this.parkingPriceRepository = parkingPriceRepository;
        this.parkingLotService = parkingLotService;
        this.converter = converter;
    }

    public void createParkingPrice(CreateParkingPriceRequest request) {
        ParkingPrice parkingPrice = new ParkingPrice();

        checkIfParkingLotAllowsMonthlyPass(request.getParkingLotId());

        parkingPrice.setDayOfWeek(request.getDayOfWeek());
        parkingPrice.setMorningHoursCost(request.getMidDayHoursCost());
        parkingPrice.setMidDayHoursCost(request.getMidDayHoursCost());
        parkingPrice.setEveningHoursCost(request.getEveningHoursCost());
        parkingPrice.setAllDayCost(Generator.calculateAllDayCost(request.getMorningHoursCost(),
                request.getMidDayHoursCost(),
                request.getEveningHoursCost()));
        parkingPrice.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));

        parkingPriceRepository.save(parkingPrice);
        log.info(BusinessLogMessage.ParkingPrice.PARKING_PRICE_SAVE_SUCCESS);
    }

    public void updateParkingPrice(final String id, UpdateParkingPriceRequest request) {
        ParkingPrice parkingPrice = findParkingPriceByParkingPriceId(id);

        checkIfParkingLotAllowsMonthlyPass(request.getParkingLotId());

        parkingPrice.setDayOfWeek(request.getDayOfWeek());
        parkingPrice.setMorningHoursCost(request.getMidDayHoursCost());
        parkingPrice.setMidDayHoursCost(request.getMidDayHoursCost());
        parkingPrice.setEveningHoursCost(request.getEveningHoursCost());
        parkingPrice.setAllDayCost(Generator.calculateAllDayCost(request.getMorningHoursCost(),
                request.getMidDayHoursCost(),
                request.getEveningHoursCost()));
        parkingPrice.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));

        parkingPriceRepository.save(parkingPrice);
        log.info(BusinessLogMessage.ParkingPrice.PARKING_PRICE_UPDATE_SUCCESS + id);
    }

    public void deleteParkingPrice(final String id) {
        ParkingPrice parkingPrice = findParkingPriceByParkingPriceId(id);

        parkingPriceRepository.delete(parkingPrice);
        log.info(BusinessLogMessage.ParkingPrice.PARKING_PRICE_DELETE_SUCCESS + id);
    }

    public ParkingPriceDto findParkingPriceById(final String id) {
        ParkingPrice parkingPrice = findParkingPriceByParkingPriceId(id);

        log.info(BusinessLogMessage.ParkingPrice.PARKING_PRICE_FOUND_SUCCESS + id);
        return converter.convert(parkingPrice);
    }

    public List<ParkingPriceDto> findAllParkingPrices() {
        List<ParkingPrice> parkingPrices = parkingPriceRepository.findAll();

        if (parkingPrices.isEmpty()) {
            log.error(BusinessLogMessage.ParkingPrice.PARKING_PRICE_LIST_EMPTY);
            throw new ParkingPriceNotFoundException(BusinessMessage.ParkingPrice.PARKING_PRICE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.ParkingPrice.PARKING_PRICE_LIST_SUCCESS);
        return converter.convert(parkingPrices);
    }

    protected ParkingPrice findParkingPriceByParkingPriceId(final String id) {
        return parkingPriceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.ParkingPrice.PARKING_PRICE_NOT_FOUND + id);
                    throw new ParkingPriceNotFoundException(BusinessMessage.ParkingPrice.PARKING_PRICE_NOT_FOUND);
                });
    }

    private void checkIfParkingLotAllowsMonthlyPass(final String parkingLotId) {
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(parkingLotId);

        if (Boolean.FALSE.equals(parkingLot.getIsMonthlyPassAllowed())) {
            log.error(BusinessLogMessage.PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
            throw new ParkingLotDoesNotAllowMonthlyPassException(BusinessMessage
                    .PARKING_LOT_DOES_NOT_ALLOW_MONTHLY_PASS);
        }
    }
}
