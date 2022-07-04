package com.example.online.shopping.dtos.ShoppingCart;

import com.example.online.shopping.models.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {

    private List<LineItem> lineItems;
}
