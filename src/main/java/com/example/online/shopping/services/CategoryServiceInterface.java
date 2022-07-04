package com.example.online.shopping.services;

import com.example.online.shopping.dtos.Category.CategoryDTO;
import com.example.online.shopping.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryServiceInterface {

    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> getCategoryById(Long id);

    ResponseEntity<Category> createCategory(CategoryDTO categoryDTO);

    ResponseEntity<Category> updateCategory(Long id, CategoryDTO categoryDTO);

    ResponseEntity<Category> deleteCategoryById(Long id);

    Category findCategoryById(Long id);

    Category findCategoryByCategoryName(String categoryName);

    boolean IsCategoryExistByName(String categoryName);
}
