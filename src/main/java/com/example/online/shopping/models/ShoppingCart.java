package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "shopping_cart_line_item_id")
    private List<LineItem> lineItems;

    @Builder
    public ShoppingCart(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
