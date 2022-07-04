package com.example.online.shopping.services;

import com.example.online.shopping.dtos.LineItem.LineItemDTO;
import com.example.online.shopping.exceptions.Product.ProductNotFoundException;
import com.example.online.shopping.models.DigitalProduct;
import com.example.online.shopping.models.LineItem;
import com.example.online.shopping.models.PhysicalProduct;
import com.example.online.shopping.models.Product;
import com.example.online.shopping.repositories.LineItemRepository;
import com.example.online.shopping.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.online.shopping.constants.ResponseConstants.UNSUCCESSFUL_GET_PRODUCT_MESSAGE;

@Service
public class LineItemService implements LineItemInterface{

    private final PhysicalProductPriceCalculatorService physicalProductPriceCalculatorService;
    private final DigitalProductPriceCalculatorService digitalProductPriceCalculatorService;
    private final LineItemRepository lineItemRepository;
    private final ProductRepository productRepository;

    public LineItemService(
            PhysicalProductPriceCalculatorService physicalProductPriceCalculatorService,
            DigitalProductPriceCalculatorService digitalProductPriceCalculatorService,
            LineItemRepository lineItemRepository, ProductRepository productRepository) {

        this.physicalProductPriceCalculatorService = physicalProductPriceCalculatorService;
        this.digitalProductPriceCalculatorService = digitalProductPriceCalculatorService;
        this.lineItemRepository = lineItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public float getPrice(LineItem lineItem) {

        Product product = lineItem.getProduct();
        int quantity = lineItem.getQuantity();

        if (product instanceof PhysicalProduct) {
            return physicalProductPriceCalculatorService.calculatePrice(product, quantity);
        } else if (product instanceof DigitalProduct) {
            return digitalProductPriceCalculatorService.calculatePrice(product, quantity);
        } else throw new RuntimeException();
    }

    @Override
    public LineItem createLineItem(LineItemDTO lineItemDTO) {
//        Product product = productRepository.findById(lineItemDTO.getProductId())
//                .orElseThrow(() -> new ProductNotFoundException(UNSUCCESSFUL_GET_PRODUCT_MESSAGE));
        Product product = lineItemDTO.getProduct();
        if(product instanceof PhysicalProduct) System.out.println(" This is an physical product in Create LineItem");
        if(product instanceof DigitalProduct) System.out.println(" This is an digital product in Create LineItem");
        LineItem lineItem = LineItem.builder()
                .quantity(lineItemDTO.getQuantity())
                .product(product)
                .build();
        return lineItemRepository.save(lineItem);
    }
}
