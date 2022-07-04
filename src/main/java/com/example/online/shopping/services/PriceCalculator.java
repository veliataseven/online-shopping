package com.example.online.shopping.services;

import com.example.online.shopping.models.Product;

public interface PriceCalculator {

    public float calculatePrice(Product product, int quantity);
}
