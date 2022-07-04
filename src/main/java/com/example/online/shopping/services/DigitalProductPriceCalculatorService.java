package com.example.online.shopping.services;

import com.example.online.shopping.models.DigitalProduct;
import com.example.online.shopping.models.Product;
import org.springframework.stereotype.Service;

@Service
public class DigitalProductPriceCalculatorService implements PriceCalculator{

    @Override
    public float calculatePrice(Product product, int quantity) {

        DigitalProduct digitalProduct = (DigitalProduct) product;
        return quantity * ((100 * digitalProduct.getBasePrice() -
                digitalProduct.getBasePrice() * digitalProduct.getProductDiscount()) / 100);
    }
}
