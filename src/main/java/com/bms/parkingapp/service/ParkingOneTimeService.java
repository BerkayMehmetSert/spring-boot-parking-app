package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.ParkingOneTimeDto;
import com.bms.parkingapp.dto.converter.ParkingOneTimeDtoConverter;
import com.bms.parkingapp.dto.request.AssignVehicleRequest;
import com.bms.parkingapp.dto.request.CreateParkingOneTimeRequest;
import com.bms.parkingapp.dto.request.UpdateParkingOneTimeRequest;
import com.bms.parkingapp.exception.ParkingOneTimeNotFoundException;
import com.bms.parkingapp.helper.DateHelper;
import com.bms.parkingapp.helper.Generator;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.Offer;
import com.bms.parkingapp.model.ParkingLot;
import com.bms.parkingapp.model.ParkingOneTime;
import com.bms.parkingapp.repository.ParkingOneTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingOneTimeService {
    private final ParkingOneTimeRepository parkingOneTimeRepository;
    private final ParkingLotService parkingLotService;
    private final VehicleService vehicleService;
    private final OfferService offerService;
    private final ParkingOneTimeDtoConverter converter;

    public ParkingOneTimeService(ParkingOneTimeRepository parkingOneTimeRepository,
                                 ParkingLotService parkingLotService,
                                 VehicleService vehicleService,
                                 OfferService offerService,
                                 ParkingOneTimeDtoConverter converter) {
        this.parkingOneTimeRepository = parkingOneTimeRepository;
        this.parkingLotService = parkingLotService;
        this.vehicleService = vehicleService;
        this.offerService = offerService;
        this.converter = converter;
    }

    public void createParkingOneTime(CreateParkingOneTimeRequest request) {
        ParkingOneTime parkingOneTime = new ParkingOneTime();

        parkingOneTime.setPayForMinHour(request.getPayForMinHour());
        parkingOneTime.setBookingForHours(request.getBookingForHours());
        parkingOneTime.setIsPaid(request.getIsPaid());
        parkingOneTime.setBasicParkingCost(request.getBasicParkingCost());
        parkingOneTime.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));

        parkingOneTimeRepository.save(parkingOneTime);
        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_SAVE_SUCCESS);
    }

    public void updateParkingOneTime(final String id, UpdateParkingOneTimeRequest request) {
        ParkingOneTime parkingOneTime = findParkingOneTimeByParkingOneTimeId(id);

        parkingOneTime.setPayForMinHour(request.getPayForMinHour());
        parkingOneTime.setBookingForHours(request.getBookingForHours());
        parkingOneTime.setIsPaid(request.getIsPaid());
        parkingOneTime.setBasicParkingCost(request.getBasicParkingCost());
        parkingOneTime.setParkingLot(parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId()));

        parkingOneTimeRepository.save(parkingOneTime);
        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_UPDATE_SUCCESS + id);
    }

    public void assignVehicleToParkingOneTime(final String id, AssignVehicleRequest request) {
        ParkingOneTime parkingOneTime = findParkingOneTimeByParkingOneTimeId(id);
        ParkingLot parkingLot = parkingLotService.findParkingLotByParkingLotId(request.getParkingLotId());
        Offer offer = offerService.findOfferByOfferCode(request.getOfferCode());

        parkingOneTime.setStartTime(DateHelper.getLocalDateTime());
        parkingOneTime.setVehicle(vehicleService.findVehicleByVehicleId(request.getVehicleId()));
        parkingOneTime.setNetCost(Generator.calculateNetCost(parkingOneTime, parkingLot, offer));
        parkingOneTime.setParkingLot(parkingLot);
        parkingOneTime.setOfferCode(request.getOfferCode());

        parkingOneTimeRepository.save(parkingOneTime);
        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_ASSIGN_VEHICLE_SUCCESS + id);
    }

    public void removeVehicleFromParkingOneTime(final String id) {
        ParkingOneTime parkingOneTime = findParkingOneTimeByParkingOneTimeId(id);

        parkingOneTime.setStartTime(null);
        parkingOneTime.setNetCost(0.0);
        parkingOneTime.setVehicle(null);

        parkingOneTimeRepository.save(parkingOneTime);
        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_REMOVE_VEHICLE_SUCCESS + id);
    }

    public void deleteParkingOneTime(final String id) {
        ParkingOneTime parkingOneTime = findParkingOneTimeByParkingOneTimeId(id);

        parkingOneTimeRepository.delete(parkingOneTime);
        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_DELETE_SUCCESS + id);
    }

    public ParkingOneTimeDto findParkingOneTimeById(final String id) {
        ParkingOneTime parkingOneTime = findParkingOneTimeByParkingOneTimeId(id);

        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_FOUND_SUCCESS + id);
        return converter.convert(parkingOneTime);
    }

    public List<ParkingOneTimeDto> findAllParkingOneTimes() {
        List<ParkingOneTime> parkingOneTimes = parkingOneTimeRepository.findAll();

        if (parkingOneTimes.isEmpty()) {
            log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_LIST_EMPTY);
            throw new ParkingOneTimeNotFoundException(BusinessMessage.ParkingOneTime.PARKING_ONE_TIME_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_LIST_SUCCESS);
        return converter.convert(parkingOneTimes);
    }

    protected ParkingOneTime findParkingOneTimeByParkingOneTimeId(String id) {
        return parkingOneTimeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.ParkingOneTime.PARKING_ONE_TIME_NOT_FOUND + id);
                    return new ParkingOneTimeNotFoundException(BusinessMessage
                            .ParkingOneTime.PARKING_ONE_TIME_NOT_FOUND);
                });
    }
}
