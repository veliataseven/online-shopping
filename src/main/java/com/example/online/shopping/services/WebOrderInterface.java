package com.example.online.shopping.services;

import com.example.online.shopping.dtos.WebOrder.WebOrderDTO;
import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.models.ShoppingCart;
import com.example.online.shopping.models.WebOrder;

public interface WebOrderInterface {

    WebOrder createWebOrder(WebOrderDTO webOrderDTO);
}
