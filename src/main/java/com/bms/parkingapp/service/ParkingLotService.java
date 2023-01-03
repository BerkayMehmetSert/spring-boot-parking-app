package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.ParkingLotDto;
import com.bms.parkingapp.dto.converter.ParkingLotDtoConverter;
import com.bms.parkingapp.dto.request.CreateParkingLotRequest;
import com.bms.parkingapp.dto.request.UpdateParkingLotRequest;
import com.bms.parkingapp.exception.ParkingLotNotFoundException;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.ParkingLot;
import com.bms.parkingapp.repository.ParkingLotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingLotDtoConverter converter;

    public ParkingLotService(ParkingLotRepository parkingLotRepository,
                             ParkingLotDtoConverter converter) {
        this.parkingLotRepository = parkingLotRepository;
        this.converter = converter;
    }

    public void createParkingLot(CreateParkingLotRequest request) {
        ParkingLot parkingLot = new ParkingLot();

        checkIfParkingLotExistsByNumber(request.getNumberOfBlocks());

        parkingLot.setNumberOfBlocks(request.getNumberOfBlocks());
        parkingLot.setIsSlotAvailable(request.getIsSlotAvailable());
        parkingLot.setAddress(request.getAddress());
        parkingLot.setZipCode(request.getZipCode());
        parkingLot.setIsReentryAllowed(request.getIsReentryAllowed());
        parkingLot.setOperatingCompany(request.getOperatingCompany());
        parkingLot.setIsValetParkingAvailable(request.getIsValetParkingAvailable());
        parkingLot.setOperationalInNight(request.getOperationalInNight());
        parkingLot.setMinimumHoursToPay(request.getMinimumHoursToPay());
        parkingLot.setIsMonthlyPassAllowed(request.getIsMonthlyPassAllowed());
        parkingLot.setMonthlyPassCost(request.getMonthlyPassCost());

        parkingLotRepository.save(parkingLot);
        log.info(BusinessLogMessage.ParkingLot.PARKING_LOT_SAVE_SUCCESS);
    }

    public void updateParkingLot(final String id, UpdateParkingLotRequest request) {
        ParkingLot parkingLot = findParkingLotByParkingLotId(id);

        if (!parkingLot.getNumberOfBlocks().equals(request.getNumberOfBlocks())) {
            checkIfParkingLotExistsByNumber(request.getNumberOfBlocks());
        }

        parkingLot.setNumberOfBlocks(request.getNumberOfBlocks());
        parkingLot.setIsSlotAvailable(request.getIsSlotAvailable());
        parkingLot.setAddress(request.getAddress());
        parkingLot.setZipCode(request.getZipCode());
        parkingLot.setIsReentryAllowed(request.getIsReentryAllowed());
        parkingLot.setOperatingCompany(request.getOperatingCompany());
        parkingLot.setIsValetParkingAvailable(request.getIsValetParkingAvailable());
        parkingLot.setOperationalInNight(request.getOperationalInNight());
        parkingLot.setMinimumHoursToPay(request.getMinimumHoursToPay());
        parkingLot.setIsMonthlyPassAllowed(request.getIsMonthlyPassAllowed());
        parkingLot.setMonthlyPassCost(request.getMonthlyPassCost());

        parkingLotRepository.save(parkingLot);
        log.info(BusinessLogMessage.ParkingLot.PARKING_LOT_UPDATE_SUCCESS + id);
    }

    public void deleteParkingLot(final String id) {
        ParkingLot parkingLot = findParkingLotByParkingLotId(id);

        parkingLotRepository.delete(parkingLot);
        log.info(BusinessLogMessage.ParkingLot.PARKING_LOT_DELETE_SUCCESS + id);
    }

    public ParkingLotDto findParkingLotById(final String id) {
        ParkingLot parkingLot = findParkingLotByParkingLotId(id);

        log.info(BusinessLogMessage.ParkingLot.PARKING_LOT_FOUND_SUCCESS + id);
        return converter.convert(parkingLot);
    }

    public List<ParkingLotDto> findAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        if (parkingLots.isEmpty()) {
            log.error(BusinessLogMessage.ParkingLot.PARKING_LOT_LIST_EMPTY);
            throw new ParkingLotNotFoundException(BusinessMessage.ParkingLot.PARKING_LOT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.ParkingLot.PARKING_LOT_LIST_SUCCESS);
        return converter.convert(parkingLots);
    }

    protected ParkingLot findParkingLotByParkingLotId(final String id) {
        return parkingLotRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.ParkingLot.PARKING_LOT_NOT_FOUND + id);
            throw new ParkingLotNotFoundException(BusinessMessage.ParkingLot.PARKING_LOT_NOT_FOUND);
        });
    }

    private void checkIfParkingLotExistsByNumber(final Integer number) {
        if (parkingLotRepository.existsByNumberOfBlocks(number)) {
            log.error(BusinessLogMessage.ParkingLot.PARKING_LOT_NUMBER_ALREADY_EXISTS, number);
        }
    }
}
