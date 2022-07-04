package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.PhysicalProduct.PhysicalProductDTO;
import com.example.online.shopping.models.PhysicalProduct;
import com.example.online.shopping.services.PhysicalProductService;
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
@RequestMapping("/api/v1/physicalProduct")
@Tag(name = "Physical Product")
public class PhysicalProductController {

    private final PhysicalProductService physicalProductService;

    public PhysicalProductController(PhysicalProductService physicalProductService) {
        this.physicalProductService = physicalProductService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get All Physical Products", description = "Get all physical products in list.")
    public ResponseEntity<List<PhysicalProduct>> getAllPhysicalProducts() {
        return physicalProductService.getAllPhysicalProducts();
    }

    @GetMapping(value = "/all/{categoryName}", produces = "application/json")
    @Operation(summary = "Get Physical Products By Category Name", description = "Get physical products by category name.")
    public ResponseEntity<List<PhysicalProduct>> getPhysicalProductsByCategoryName(
            @PathVariable(name = "categoryName", required = true)
            @Size(min = 2, message = "categoryName should have at least 2 characters") String categoryName) {
        return physicalProductService.getPhysicalProductsByCategoryName(categoryName);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get Physical Product By Id", description = "Get a physical product by id.")
    public ResponseEntity<PhysicalProduct> getPhysicalProductById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return physicalProductService.getPhysicalProductById(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create Physical Product", description = "Create a physical product with physical product data.")
    public ResponseEntity<PhysicalProduct> createPhysicalProduct(@RequestBody @Valid PhysicalProductDTO physicalProductDTO) {
        return physicalProductService.createPhysicalProduct(physicalProductDTO);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update Physical Product", description = "Update a physical product with physical product data.")
    public ResponseEntity<PhysicalProduct> updateDigitalProduct(
            @Min(value = 1L, message = "id must be greater than or equal to 1!")
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody @Valid PhysicalProductDTO physicalProductDTO) {
        return physicalProductService.updatePhysicalProduct(id, physicalProductDTO);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @Operation(summary = "Delete Physical Product By Id", description = "Delete physical product by id.")
    public ResponseEntity<PhysicalProduct> deletePhysicalProduct(
            @Min(value = 1L, message = "id must be greater than or equal to 1!")
            @PathVariable(name = "id", required = true) Long id){
        return physicalProductService.deletePhysicalProduct(id);
    }
}
