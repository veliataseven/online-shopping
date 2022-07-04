package com.example.online.shopping.models;

import com.example.online.shopping.enums.ShippingCategory;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "physical_products")
public class PhysicalProduct extends Product implements Shipable {

    @Column(name = "weight")
    private float weight;

    @Column(name = "shipping_category")
    @Enumerated(value = EnumType.STRING)
    private ShippingCategory shippingCategory;

    @Builder
    public PhysicalProduct(String productName,
                           float basePrice,
                           float productDiscount,
                           String description,
                           String imageUrl,
                           Category category,
                           float weight,
                           ShippingCategory shippingCategory) {
        super(productName,
                basePrice,
                productDiscount,
                description,
                imageUrl,
                category);
        this.weight = weight;
        this.shippingCategory = shippingCategory;
    }

    @Override
    public void ship() {
        switch (shippingCategory) {
            case BULKY: // consign to standard courier
            case STANDARD: // book large-capacity vehicle and movers
            case PREMIUM: // buy insurance and consign to secure courier
        }
    }

    @Override
    public String toString() {
        return "PhysicalProduct{" +
                "weight=" + weight +
                ", shippingCategory=" + shippingCategory +
                '}';
    }
}
