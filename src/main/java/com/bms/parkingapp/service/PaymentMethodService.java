package com.bms.parkingapp.service;

import com.bms.parkingapp.dto.PaymentMethodDto;
import com.bms.parkingapp.dto.converter.PaymentMethodDtoConverter;
import com.bms.parkingapp.dto.request.CreatePaymentMethodRequest;
import com.bms.parkingapp.dto.request.UpdatePaymentMethodRequest;
import com.bms.parkingapp.exception.PaymentMethodNotFoundException;
import com.bms.parkingapp.helper.message.BusinessLogMessage;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.PaymentMethod;
import com.bms.parkingapp.repository.PaymentMethodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final CustomerService customerService;
    private final PaymentMethodDtoConverter converter;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository,
                                CustomerService customerService,
                                PaymentMethodDtoConverter converter) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public void createPaymentMethod(CreatePaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();

        checkIfPaymentMethodExists(request.getCardNumber());

        paymentMethod.setCardType(request.getCardType());
        paymentMethod.setCardNumber(request.getCardNumber());
        paymentMethod.setExpiryMonth(request.getExpiryMonth());
        paymentMethod.setExpiryYear(request.getExpiryYear());
        paymentMethod.setSecurityCode(request.getSecurityCode());
        paymentMethod.setCustomer(customerService.findCustomerByCustomerId(request.getCustomerId()));

        paymentMethodRepository.save(paymentMethod);
        log.info(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_SAVE_SUCCESS);
    }

    public void updatePaymentMethod(final String id, UpdatePaymentMethodRequest request) {
        PaymentMethod paymentMethod = findPaymentMethodByPaymentMethodId(id);

        if (!paymentMethod.getCardNumber().equals(request.getCardNumber())) {
            checkIfPaymentMethodExists(request.getCardNumber());
        }

        paymentMethod.setCardType(request.getCardType());
        paymentMethod.setCardNumber(request.getCardNumber());
        paymentMethod.setExpiryMonth(request.getExpiryMonth());
        paymentMethod.setExpiryYear(request.getExpiryYear());
        paymentMethod.setSecurityCode(request.getSecurityCode());
        paymentMethod.setCustomer(customerService.findCustomerByCustomerId(request.getCustomerId()));

        paymentMethodRepository.save(paymentMethod);
    }

    public void deletePaymentMethod(final String id) {
        PaymentMethod paymentMethod = findPaymentMethodByPaymentMethodId(id);

        log.info(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_DELETE_SUCCESS + id);
        paymentMethodRepository.delete(paymentMethod);
    }

    public PaymentMethodDto findPaymentMethodById(final String id) {
        PaymentMethod paymentMethod = findPaymentMethodByPaymentMethodId(id);

        log.info(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_FOUND_SUCCESS + id);
        return converter.convert(paymentMethod);
    }

    public List<PaymentMethodDto> findAllPaymentMethods(){
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        if (paymentMethods.isEmpty()) {
            log.error(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_LIST_EMPTY);
            throw new PaymentMethodNotFoundException(BusinessMessage.PaymentMethod.PAYMENT_METHOD_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_LIST_SUCCESS);
        return converter.convert(paymentMethods);
    }

    protected PaymentMethod findPaymentMethodByPaymentMethodId(String id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_NOT_FOUND + id);
                    throw new PaymentMethodNotFoundException(BusinessMessage.PaymentMethod.PAYMENT_METHOD_NOT_FOUND);
                });
    }

    private void checkIfPaymentMethodExists(String number) {
        if (paymentMethodRepository.existsByCardNumber(number)) {
            log.error(BusinessLogMessage.PaymentMethod.PAYMENT_METHOD_ALREADY_EXISTS + number);
            throw new PaymentMethodNotFoundException(BusinessMessage.PaymentMethod.PAYMENT_METHOD_ALREADY_EXISTS);
        }
    }
}
