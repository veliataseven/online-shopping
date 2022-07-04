package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Table(name = "eft_account")
public class EftAccount extends PaymentMethod {

    @Setter
    @Column(name = "email_address")
    private String emailAddress;

    @Builder
    public EftAccount(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public Payment makePayment(int value) {
        return new Payment(value, this);
    }
}
