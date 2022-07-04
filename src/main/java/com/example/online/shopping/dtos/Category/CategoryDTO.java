package com.example.online.shopping.dtos.Category;

import com.example.online.shopping.models.Product;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotEmpty(message = "category name should not be null or empty")
    private String categoryName;

    private String description;

    // @Url
    private String imageUrl;

    private List<Product> products;
}
