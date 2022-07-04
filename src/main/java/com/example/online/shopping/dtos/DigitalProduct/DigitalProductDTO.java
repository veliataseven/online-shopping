package com.example.online.shopping.dtos.DigitalProduct;

import com.example.online.shopping.dtos.Product.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class DigitalProductDTO extends ProductDTO {

    public DigitalProductDTO(
            @NotEmpty(message = "product name should not be null or empty")
            @Size(min = 2, message = "product name should have at least 2 characters")
                    String productName,
            @Min(0)
                    float basePrice,
            @Min(0)
                    float productDiscount,
            @NotEmpty(message = "product description should not be null or empty")
            @Size(min = 2, message = "product description should have at least 2 characters")
                    String description,
            @NotEmpty(message = "product imageURL should not be null or empty")
                    String imageUrl,
            @NotEmpty(message = "category name should not be null or empty")
            @Size(min = 2, message = "category name should have at least 2 characters")
                    String categoryName) {
        super(productName, basePrice, productDiscount, description, imageUrl, categoryName);
    }

}
