package com.example.online.shopping.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "products")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
@Getter
@JsonDeserialize(as = PhysicalProduct.class)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "product_name")
    private String productName;

    @Setter
    @Column(name = "base_price")
    private float basePrice;

    @Setter
    @Column(name = "product_discount")
    private float productDiscount;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    public Product(String productName,
                   float basePrice,
                   float productDiscount,
                   String description,
                   String imageUrl,
                   Category category) {

        this.productName = productName;
        this.basePrice = basePrice;
        this.productDiscount = productDiscount;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
