package com.example.online.shopping.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@NoArgsConstructor
public abstract class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Getter
    @Column(name = "id")
    private Long id;

    public abstract Payment makePayment(int value);
}
