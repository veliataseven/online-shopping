package com.example.online.shopping.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "digital_products")
@Data
@NoArgsConstructor
public class DigitalProduct extends Product implements Shipable {

    @Builder
    public DigitalProduct(String productName,
                          float basePrice,
                          float productDiscount,
                          String description,
                          String imageUrl,
                          Category category) {

        super(productName,
                basePrice,
                productDiscount,
                description,
                imageUrl,
                category);
    }

    @Override
    public void ship() {
        // make the product downloadable.
    }

    @Override
    public String toString() {
        return "DigitalProduct{}";
    }
}
