package com.example.online.shopping.services;

import com.example.online.shopping.constants.ShippingCostFactor;
import com.example.online.shopping.enums.ShippingCategory;
import com.example.online.shopping.models.PhysicalProduct;
import com.example.online.shopping.models.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PhysicalProductPriceCalculatorService implements PriceCalculator {

    private final Map<ShippingCategory, Integer> shippingCostFactors = new HashMap<>();

    public PhysicalProductPriceCalculatorService() {

        shippingCostFactors.put(ShippingCategory.BULKY, ShippingCostFactor.BULK_COST_FACTOR);
        shippingCostFactors.put(ShippingCategory.STANDARD, ShippingCostFactor.STANDARD_COST_FACTOR);
        shippingCostFactors.put(ShippingCategory.PREMIUM, ShippingCostFactor.PREMIUM_COST_FACTOR);
    }

    @Override
    public float calculatePrice(Product product, int quantity) {

        PhysicalProduct physicalProduct = (PhysicalProduct) product;

        float weight = physicalProduct.getWeight();
        ShippingCategory shippingCategory = physicalProduct.getShippingCategory();
        float shippingCost = shippingCostFactors.get(shippingCategory) * weight / 100;

        return quantity * (shippingCost + (100 * physicalProduct.getBasePrice() -
                physicalProduct.getBasePrice() * physicalProduct.getProductDiscount()) / 100);
    }
}
