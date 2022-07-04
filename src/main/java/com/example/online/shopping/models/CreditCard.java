package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Table(name = "credit_card")
public class CreditCard extends PaymentMethod {

    @Setter
    @Column(name = "credit_card_number")
    private long creditCardNumber;

    @Builder
    public CreditCard(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public Payment makePayment(int value) {
        return new Payment(value, this);
    }
}
