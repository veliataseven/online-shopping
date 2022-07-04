package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.Payment.PaymentDTO;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/payment")
@Tag(name = "Payment")
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create Payment", description = "Create a payment with payment data.")
    ResponseEntity<Payment> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        Payment payment = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<Payment>(payment, HttpStatus.CREATED);
    }
}
