package com.example.online.shopping.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "category_name")
    private String categoryName;

    @Setter
    @Column(name = "description")
    private String description;

    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Setter
    @Column(name = "products")
    @OneToMany(mappedBy = "category",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<Product> products;

    @Builder
    public Category(String categoryName, String description, String imageUrl, List<Product> products) {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.products = products;
    }
}
