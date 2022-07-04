package com.example.online.shopping.dtos.Payment;

import com.example.online.shopping.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @Min(0)
    private float value;

    private PaymentMethod paymentMethod;
}
