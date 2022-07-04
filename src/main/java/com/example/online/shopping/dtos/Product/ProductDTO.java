package com.example.online.shopping.dtos.Product;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotEmpty(message = "product name should not be null or empty")
    @Size(min = 2, message = "product name should have at least 2 characters")
    private String productName;

    @NotNull
    @Min(0)
    private float basePrice;

    @NotNull
    @Min(0)
    private float productDiscount;

    @NotEmpty(message = "product description should not be null or empty")
    @Size(min = 2, message = "product description should have at least 2 characters")
    private String description;

//    @URL use pattern to validate url later
    @NotEmpty(message = "product imageURL should not be null or empty")
    private String imageUrl;

    @NotEmpty(message = "category name should not be null or empty")
    @Size(min = 2, message = "category name should have at least 2 characters")
    private String categoryName;
}
