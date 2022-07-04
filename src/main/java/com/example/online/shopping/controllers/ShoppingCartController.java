package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.ShoppingCart.ShoppingCartDTO;
import com.example.online.shopping.models.ShoppingCart;
import com.example.online.shopping.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/shoppingCart")
@Validated
@Tag(name = "Shopping Cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create LineItem", description = "Create a line item with line item data.")
    ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody @Valid ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(shoppingCartDTO);
        return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.CREATED);
    }
}


