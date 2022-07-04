package com.example.online.shopping.services;

import com.example.online.shopping.dtos.Payment.PaymentDTO;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.models.PaymentMethod;
import com.example.online.shopping.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentInterface {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(PaymentDTO paymentDTO) {

        Payment payment = Payment.builder()
                .value(paymentDTO.getValue())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .build();

        return paymentRepository.save(payment);
    }
}
