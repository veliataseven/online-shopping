package com.example.online.shopping.models;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "line_item")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Getter
    @Setter
    private int quantity;

    @Builder
    public LineItem(Product product, int quantity) {
        if(product instanceof PhysicalProduct) System.out.println(" This is an physical product in Constructor LineItem");
        if(product instanceof DigitalProduct) System.out.println(" This is an digital product in Constructor LineItem");

        this.product = product;
        this.quantity = quantity;
    }
}
