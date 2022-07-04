package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.DigitalProduct.DigitalProductDTO;
import com.example.online.shopping.models.DigitalProduct;
import com.example.online.shopping.services.DigitalProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/digitalProduct")
@Tag(name = "Digital Product")
public class DigitalProductController {

    private final DigitalProductService digitalProductService;

    public DigitalProductController(DigitalProductService digitalProductService) {
        this.digitalProductService = digitalProductService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get All Digital Products", description = "Get all digital products in list.")
    public ResponseEntity<List<DigitalProduct>> getAllDigitalProducts() {
        return digitalProductService.getAllDigitalProducts();
    }

    @GetMapping(value = "/all/{categoryName}", produces = "application/json")
    @Operation(summary = "Get Digital Products By Category Name", description = "Get digital products by category name.")
    public ResponseEntity<List<DigitalProduct>> getDigitalProductsByCategoryName(
            @PathVariable(name = "categoryName", required = true)
            @Size(min = 2, message = "categoryName should have at least 2 characters") String categoryName) {
        return digitalProductService.getDigitalProductsByCategoryName(categoryName);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get Digital Product By Id", description = "Get a digital product by id.")
    public ResponseEntity<DigitalProduct> getDigitalProductById(
            @Min(value = 1L, message = "id must be greater than or equal to 1 !")
            @PathVariable(name = "id", required = true) Long id) {
        return digitalProductService.getDigitalProductById(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create Digital Product", description = "Create a digital product with digital product data.")
    public ResponseEntity<DigitalProduct> createDigitalProduct(
            @RequestBody @Valid DigitalProductDTO digitalProductDTO) {
        return digitalProductService.createDigitalProduct(digitalProductDTO);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update Digital Product", description = "Update a digital product with digital product data.")
    public ResponseEntity<DigitalProduct> updateDigitalProduct(
            @Min(value = 1L, message = "id must be greater than or equal to 1!")
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody @Valid DigitalProductDTO digitalProductDTO) {
        return digitalProductService.updateDigitalProduct(id,digitalProductDTO);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @Operation(summary = "Delete Digital Product By Id", description = "Delete digital product by id.")
    public ResponseEntity<DigitalProduct> deleteDigitalProduct(
            @Min(value = 1L, message = "id must be greater than or equal to 1!")
            @PathVariable(name = "id", required = true) Long id){
        return digitalProductService.deleteDigitalProduct(id);
    }
}
