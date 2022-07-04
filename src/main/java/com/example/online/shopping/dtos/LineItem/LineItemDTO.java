package com.example.online.shopping.dtos.LineItem;

import com.example.online.shopping.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineItemDTO {

    private Product product;

    @Min(0)
    private int quantity;
}
