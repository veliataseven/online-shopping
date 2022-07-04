package com.example.online.shopping.dtos.PhysicalProduct;

import com.example.online.shopping.dtos.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalProductDTO extends ProductDTO {

    @Min(0)
    private float weight;

    @NotEmpty(message = "Shipping category should not be null or empty")
    private String shippingCategory;

    @Builder
    public PhysicalProductDTO(
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
                    String categoryName,
            float weight,
            String shippingCategory) {
        super(productName, basePrice, productDiscount, description, imageUrl, categoryName);
        this.weight = weight;
        this.shippingCategory = shippingCategory;
    }

}
