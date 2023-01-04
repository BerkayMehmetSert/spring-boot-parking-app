package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.ParkingMonthlyDto;
import com.bms.parkingapp.dto.converter.ParkingMonthlyDtoConverter;
import com.bms.parkingapp.dto.request.CreateParkingMonthlyRequest;
import com.bms.parkingapp.dto.request.UpdateParkingMonthlyRequest;
import com.bms.parkingapp.exception.ParkingMonthlyNotFoundException;
import com.bms.parkingapp.helper.DateHelper;
import com.bms.parkingapp.helper.Generator;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.Customer;
import com.bms.parkingapp.model.ParkingLot;
import com.bms.parkingapp.model.ParkingMonthly;
import com.bms.parkingapp.repository.ParkingMonthlyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingMonthlyService {
    private final ParkingMonthlyRepository parkingMonthlyRepository;
    private final ParkingLotService parkingLotService;
    private final CustomerService customerService;
    private final ParkingMonthlyDtoConverter converter;

    public ParkingMonthlyService(ParkingMonthlyRepository parkingMonthlyRepository,
                                 ParkingLotService parkingLotService,
                                 CustomerService customerService,
                                 ParkingMonthlyDtoConverter converter) {
        this.parkingMonthlyRepository = parkingMonthlyRepository;
        this.parkingLotService = parkingLotService;
        this.customerService = customerService;
        this.converter = converter;
    }

    public void createParkingMonthly(CreateParkingMonthlyRequest request) {
        ParkingMonthly parkingMonthly = new ParkingMonthly();
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId());
        Customer customer = customerService.findCustomerByCustomerId(request.getCustomerId());

        parkingMonthly.setPurchaseDate(DateHelper.getLocalDate());
        parkingMonthly.setStartDate(request.getStartDate());
        parkingMonthly.setDurationInDays(request.getDurationInDays());
        parkingMonthly.setCost(Generator.generateCost(parkingMonthly, parkingLot, customer));
        parkingMonthly.setParkingLot(parkingLot);
        parkingMonthly.setCustomer(customer);

        parkingMonthlyRepository.save(parkingMonthly);
        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_SAVE_SUCCESS);
    }

    public void updateParkingMonthly(final String id, UpdateParkingMonthlyRequest request) {
        ParkingMonthly parkingMonthly = findParkingMonthlyByParkingMonthlyId(id);

        parkingMonthly.setPurchaseDate(DateHelper.getLocalDate());
        parkingMonthly.setStartDate(request.getStartDate());
        parkingMonthly.setDurationInDays(request.getDurationInDays());
        parkingMonthly.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));
        parkingMonthly.setCustomer(customerService.findCustomerByCustomerId(request.getCustomerId()));

        parkingMonthlyRepository.save(parkingMonthly);
        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_UPDATE_SUCCESS + id);
    }

    public void updateParkingMonthlyParkingLot(final String id, final String parkingLotId) {
        ParkingMonthly parkingMonthly = findParkingMonthlyByParkingMonthlyId(id);
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(parkingLotId);

        parkingMonthly.setParkingLot(parkingLot);
        parkingMonthly.setCost(Generator.generateCost(parkingMonthly, parkingLot, parkingMonthly.getCustomer()));

        parkingMonthlyRepository.save(parkingMonthly);
        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_UPDATE_SUCCESS + id);
    }

    public void assignParkingMonthlyToDuration(final String id, final Integer durationInDays) {
        ParkingMonthly parkingMonthly = findParkingMonthlyByParkingMonthlyId(id);

        parkingMonthly.setDurationInDays(durationInDays);
        parkingMonthly.setCost(Generator.generateCost(parkingMonthly, parkingMonthly.getParkingLot(), parkingMonthly.getCustomer()));

        parkingMonthlyRepository.save(parkingMonthly);
        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_UPDATE_SUCCESS + id);
    }

    public void deleteParkingMonthly(final String id) {
        ParkingMonthly parkingMonthly = findParkingMonthlyByParkingMonthlyId(id);

        parkingMonthlyRepository.delete(parkingMonthly);
        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_DELETE_SUCCESS + id);
    }

    public ParkingMonthlyDto findParkingMonthlyById(final String id) {
        ParkingMonthly parkingMonthly = findParkingMonthlyByParkingMonthlyId(id);

        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_FOUND_SUCCESS + id);
        return converter.convert(parkingMonthly);
    }

    public List<ParkingMonthlyDto> findAllParkingMonthlies() {
        List<ParkingMonthly> parkingMonthlies = parkingMonthlyRepository.findAll();

        if (parkingMonthlies.isEmpty()) {
            log.error(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_LIST_EMPTY);
            throw new ParkingMonthlyNotFoundException(BusinessMessage.ParkingMonthly.PARKING_MONTHLY_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_LIST_SUCCESS);
        return converter.convert(parkingMonthlies);
    }

    protected ParkingMonthly findParkingMonthlyByParkingMonthlyId(final String id) {
        return parkingMonthlyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.ParkingMonthly.PARKING_MONTHLY_NOT_FOUND + id);
                    throw new ParkingMonthlyNotFoundException(BusinessMessage.ParkingMonthly.PARKING_MONTHLY_NOT_FOUND);
                });
    }

}
