package com.bms.parkingapp.controller;

import com.bms.parkingapp.dto.PaymentMethodDto;
import com.bms.parkingapp.dto.request.CreatePaymentMethodRequest;
import com.bms.parkingapp.dto.request.UpdatePaymentMethodRequest;
import com.bms.parkingapp.helper.message.ControllerLogMessage;
import com.bms.parkingapp.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
@Slf4j
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<Void> createPaymentMethod(@RequestBody CreatePaymentMethodRequest request) {
        paymentMethodService.createPaymentMethod(request);

        log.info(ControllerLogMessage.PaymentMethod.PAYMENT_METHOD_SAVE_SUCCESS);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaymentMethod(@PathVariable String id,
                                                     @RequestBody UpdatePaymentMethodRequest request) {
        paymentMethodService.updatePaymentMethod(id, request);

        log.info(ControllerLogMessage.PaymentMethod.PAYMENT_METHOD_UPDATE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable String id) {
        paymentMethodService.deletePaymentMethod(id);

        log.info(ControllerLogMessage.PaymentMethod.PAYMENT_METHOD_DELETE_SUCCESS + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> findPaymentMethodById(@PathVariable String id) {
        PaymentMethodDto paymentMethod = paymentMethodService.findPaymentMethodById(id);

        log.info(ControllerLogMessage.PaymentMethod.PAYMENT_METHOD_FOUND_SUCCESS + id);
        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAllPaymentMethods() {
        List<PaymentMethodDto> paymentMethods = paymentMethodService.findAllPaymentMethods();

        log.info(ControllerLogMessage.PaymentMethod.PAYMENT_METHOD_LIST_SUCCESS);
        return ResponseEntity.ok(paymentMethods);
    }
}
