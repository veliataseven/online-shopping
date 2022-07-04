package com.example.online.shopping.services;

import com.example.online.shopping.dtos.ShoppingCart.ShoppingCartDTO;
import com.example.online.shopping.models.LineItem;
import com.example.online.shopping.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartInterface {

    ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO);

    ShoppingCart addLineItem(ShoppingCart shoppingCart, LineItem lineItem);

    float getTotalCost(ShoppingCart shoppingCart);
}
