package com.example.online.shopping.services;

import com.example.online.shopping.dtos.Payment.PaymentDTO;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.models.PaymentMethod;

public interface PaymentInterface {

    Payment createPayment(PaymentDTO paymentDTO);
}
