package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "web_order")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class WebOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Setter
    private AppUser user;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    @Setter
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    @Setter
    private ShoppingCart shoppingCart;

    @Builder
    public WebOrder(AppUser user, Payment payment, ShoppingCart shoppingCart) {
        this.user = user;
        this.payment = payment;
        this.shoppingCart = shoppingCart;
    }
}
