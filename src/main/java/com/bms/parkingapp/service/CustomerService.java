package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.CustomerDto;
import com.bms.parkingapp.dto.converter.CustomerDtoConverter;
import com.bms.parkingapp.dto.request.CreateCustomerRequest;
import com.bms.parkingapp.dto.request.UpdateCustomerRequest;
import com.bms.parkingapp.exception.CustomerNotFoundException;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.Customer;
import com.bms.parkingapp.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public void createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer();

        checkIfCustomerExist(request.getFirstName(), request.getLastName());

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setBillingAddress(request.getBillingAddress());
        customer.setContactNumber(request.getContactNumber());
        customer.setRegistrationDate(request.getRegistrationDate());
        customer.setIsRegularCustomer(false);

        customerRepository.save(customer);
        log.info(BusinessLogMessage.Customer.CUSTOMER_SAVE_SUCCESS);
    }

    public void updateCustomer(final String id, UpdateCustomerRequest request) {
        Customer customer = findCustomerByCustomerId(id);

        if (!customer.getFirstName().equals(request.getFirstName()) || !customer.getLastName().equals(request.getLastName())) {
            checkIfCustomerExist(request.getFirstName(), request.getLastName());
        }

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setBillingAddress(request.getBillingAddress());
        customer.setContactNumber(request.getContactNumber());
        customer.setRegistrationDate(request.getRegistrationDate());

        customerRepository.save(customer);
        log.info(BusinessLogMessage.Customer.CUSTOMER_UPDATE_SUCCESS + id);
    }

    public void updateCustomerRegularStatus(final String id, boolean isRegularCustomer) {
        Customer customer = findCustomerByCustomerId(id);

        customer.setIsRegularCustomer(isRegularCustomer);

        customerRepository.save(customer);
        log.info(BusinessLogMessage.Customer.CUSTOMER_UPDATE_REGULAR_STATUS_SUCCESS + id);
    }

    public void deleteCustomer(final String id) {
        Customer customer = findCustomerByCustomerId(id);

        customerRepository.delete(customer);
        log.info(BusinessLogMessage.Customer.CUSTOMER_DELETE_SUCCESS + id);
    }

    public CustomerDto findCustomerById(final String id) {
        Customer customer = findCustomerByCustomerId(id);

        log.info(BusinessLogMessage.Customer.CUSTOMER_FOUND_SUCCESS + id);
        return converter.convert(customer);
    }

    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            log.error(BusinessLogMessage.Customer.CUSTOMER_LIST_EMPTY);
            throw new CustomerNotFoundException(BusinessMessage.Customer.CUSTOMER_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Customer.CUSTOMER_LIST_SUCCESS);
        return converter.convert(customers);
    }

    protected Customer findCustomerByCustomerId(final String id) {
        return customerRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.Customer.CUSTOMER_NOT_FOUND + id);
            throw new CustomerNotFoundException(BusinessMessage.Customer.CUSTOMER_NOT_FOUND);
        });
    }

    private void checkIfCustomerExist(final String firstName, final String lastName) {
        if (customerRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)) {
            log.error(BusinessLogMessage.Customer.CUSTOMER_ALREADY_EXISTS + firstName + " " + lastName);
            throw new CustomerNotFoundException(BusinessMessage.Customer.CUSTOMER_ALREADY_EXISTS);
        }
    }
}
