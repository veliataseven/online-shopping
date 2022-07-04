package com.example.online.shopping.controllers;

import com.example.online.shopping.dtos.Category.CategoryDTO;
import com.example.online.shopping.models.Category;
import com.example.online.shopping.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/category")
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @Operation(summary = "Get All Categories", description = "Get all categories in list.")
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get Category By Id", description = "Get a category by id.")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create Category", description = "Create a category with category data.")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update Category", description = "Update category with category data.")
    public ResponseEntity<Category> updateCategory(
            @Min(value = 1L, message = "id must be greater than or equal to 1 !")
            @PathVariable(name = "id", required = true) Long id,
            @RequestBody @Valid CategoryDTO categoryDTO) {
        return categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    @Operation(summary = "Delete Category By Id", description = "Delete category by id.")
    public ResponseEntity<Category> deleteCategoryById(
            @PathVariable(name = "id", required = true)
            @Min(value = 1L, message = "id must be greater than or equal to 1 !") Long id) {
        return categoryService.deleteCategoryById(id);
    }
}
