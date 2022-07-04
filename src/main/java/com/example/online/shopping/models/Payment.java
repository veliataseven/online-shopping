package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    @Setter
    private float value;

    @OneToOne
    @JoinColumn(name = "payment_method_id")
    @Setter
    private PaymentMethod paymentMethod;

    @Builder
    public Payment(float value, PaymentMethod paymentMethod) {
        this.value = value;
        this.paymentMethod = paymentMethod;
    }
}
