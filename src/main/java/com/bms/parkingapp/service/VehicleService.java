package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.VehicleDto;
import com.bms.parkingapp.dto.converter.VehicleDtoConverter;
import com.bms.parkingapp.dto.request.CreateVehicleRequest;
import com.bms.parkingapp.dto.request.UpdateVehicleRequest;
import com.bms.parkingapp.exception.VehicleAlreadyExistException;
import com.bms.parkingapp.exception.VehicleNotFoundException;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.Vehicle;
import com.bms.parkingapp.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CustomerService customerService;
    private final VehicleDtoConverter converter;

    public VehicleService(VehicleRepository vehicleRepository,
                          CustomerService customerService,
                          VehicleDtoConverter converter) {
        this.vehicleRepository = vehicleRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public void createVehicle(CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();

        checkIfVehicleExist(request.getVehicleNumber());

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setCustomer(customerService.findCustomerByCustomerId(request.getCustomerId()));

        vehicleRepository.save(vehicle);
        log.info(BusinessLogMessage.Vehicle.VEHICLE_SAVE_SUCCESS);
    }

    public void updateVehicle(final String id, UpdateVehicleRequest request) {
        Vehicle vehicle = findVehicleByVehicleId(id);

        if (!vehicle.getVehicleNumber().equals(request.getVehicleNumber())) {
            checkIfVehicleExist(request.getVehicleNumber());
        }

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setCustomer(customerService.findCustomerByCustomerId(request.getCustomerId()));

        vehicleRepository.save(vehicle);
        log.info(BusinessLogMessage.Vehicle.VEHICLE_UPDATE_SUCCESS + id);
    }

    public void deleteVehicle(final String id) {
        Vehicle vehicle = findVehicleByVehicleId(id);

        vehicleRepository.delete(vehicle);
        log.info(BusinessLogMessage.Vehicle.VEHICLE_DELETE_SUCCESS + id);
    }

    public VehicleDto findVehicleById(final String id) {
        Vehicle vehicle = findVehicleByVehicleId(id);

     log.info(BusinessLogMessage.Vehicle.VEHICLE_FOUND_SUCCESS + id);
        return converter.convert(vehicle);
    }

    public List<VehicleDto> findAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            log.error(BusinessLogMessage.Vehicle.VEHICLE_LIST_EMPTY);
            throw new VehicleNotFoundException(BusinessMessage.Vehicle.VEHICLE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Vehicle.VEHICLE_LIST_SUCCESS);
        return converter.convert(vehicles);
    }

    protected Vehicle findVehicleByVehicleId(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Vehicle.VEHICLE_NOT_FOUND + id);
                    return new VehicleNotFoundException(BusinessLogMessage.Vehicle.VEHICLE_NOT_FOUND);
                });
    }

    private void checkIfVehicleExist(final String vehicleNumber) {
        if (vehicleRepository.existsByVehicleNumber(vehicleNumber)) {
            log.info(BusinessLogMessage.Vehicle.VEHICLE_ALREADY_EXISTS + vehicleNumber);
            throw new VehicleAlreadyExistException(BusinessMessage.Vehicle.VEHICLE_ALREADY_EXISTS);
        }
    }
}
