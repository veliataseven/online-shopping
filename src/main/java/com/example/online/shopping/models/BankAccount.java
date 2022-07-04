package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Table(name = "bank_account")
public class BankAccount extends PaymentMethod {

    @Setter
    @Column(name = "account_number")
    private long accountNumber;

    @Builder
    public BankAccount(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public Payment makePayment(int value) {
        return new Payment(value, this);
    }
}
