package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.CustomerDto;
import com.bms.parkingapp.dto.request.CreateCustomerRequest;
import com.bms.parkingapp.dto.request.UpdateCustomerRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerRequest request) {
        customerService.createCustomer(request);

        log.info(ControllerLogMessage.Customer.CUSTOMER_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable String id,
                                               @RequestBody UpdateCustomerRequest request) {
        customerService.updateCustomer(id, request);

        log.info(ControllerLogMessage.Customer.CUSTOMER_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateCustomerRegularStatus(@PathVariable String id,
                                                            @RequestParam boolean isRegularCustomer) {
        customerService.updateCustomerRegularStatus(id, isRegularCustomer);

        log.info(ControllerLogMessage.Customer.CUSTOMER_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);

        log.info(ControllerLogMessage.Customer.CUSTOMER_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable String id) {
        CustomerDto customer = customerService.findCustomerById(id);

        log.info(ControllerLogMessage.Customer.CUSTOMER_FOUND_SUCCESS + id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<CustomerDto> customers = customerService.findAllCustomers();

        log.info(ControllerLogMessage.Customer.CUSTOMER_LIST_SUCCESS);
        return ResponseEntity.ok(customers);
    }
}
