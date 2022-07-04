package com.example.online.shopping.dtos.WebOrder;

import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.models.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebOrderDTO {

    private AppUser user;

    private Payment payment;

    private ShoppingCart shoppingCart;
}
