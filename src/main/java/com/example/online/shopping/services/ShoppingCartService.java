package com.example.online.shopping.services;

import com.example.online.shopping.dtos.ShoppingCart.ShoppingCartDTO;
import com.example.online.shopping.models.LineItem;
import com.example.online.shopping.models.ShoppingCart;
import com.example.online.shopping.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCartService implements ShoppingCartInterface {

    private final ShoppingCartRepository shoppingCartRepository;
    private final LineItemService lineItemService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, LineItemService lineItemService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.lineItemService = lineItemService;
    }

    @Override
    @Transactional
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .lineItems(shoppingCartDTO.getLineItems())
                .build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart addLineItem(ShoppingCart shoppingCart, LineItem lineItem) {
        shoppingCart.getLineItems().add(lineItem);
        return shoppingCart;
    }

    @Override
    public float getTotalCost(ShoppingCart shoppingCart) {
        return (float) shoppingCart.getLineItems().stream()
                .mapToDouble(lineItem -> lineItemService.getPrice(lineItem))
                .sum();
    }


}
