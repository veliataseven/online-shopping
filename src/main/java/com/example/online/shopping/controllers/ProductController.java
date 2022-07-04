package com.example.online.shopping.controllers;

import com.example.online.shopping.models.Product;
import com.example.online.shopping.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get All Products", description = "Get all products in list.")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/all/{categoryName}", produces = "application/json")
    @Operation(summary = "Get Products By Category Name", description = "Get products by category name.")
    public ResponseEntity<List<Product>> getProductsByCategoryName(
            @PathVariable(name = "categoryName", required = true)
            @Size(min = 2, message = "categoryName should have at least 2 characters") String categoryName) {
        return productService.getProductsByCategoryName(categoryName);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get Product By Id", description = "Get a product by id.")
    public ResponseEntity<Product> getProductById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    @Operation(summary = "Delete Product By Id", description = "Delete product by id.")
    public ResponseEntity<Product> deleteProductById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1!") Long id) {
        return productService.deleteProduct(id);
    }
}
