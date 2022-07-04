package com.example.online.shopping.services;

import com.example.online.shopping.dtos.Category.CategoryDTO;
import com.example.online.shopping.exceptions.Category.CategoryIsAlreadyExistException;
import com.example.online.shopping.exceptions.Category.CategoryNotFoundException;
import com.example.online.shopping.exceptions.Category.NoCategoryFoundInListException;
import com.example.online.shopping.models.Category;
import com.example.online.shopping.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
@Transactional
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new NoCategoryFoundInListException(UNSUCCESSFUL_GET_ALL_CATEGORIES_MESSAGE);
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) {
        Category category = findCategoryById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> createCategory(CategoryDTO categoryDTO) {
        boolean isCategoryExistByName = IsCategoryExistByName(categoryDTO.getCategoryName());
        if (isCategoryExistByName) throw new CategoryIsAlreadyExistException(UNSUCCESSFUL_CATEGORY_CREATE_MESSAGE);

        Category newCategory = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .description(categoryDTO.getDescription())
                .imageUrl(categoryDTO.getImageUrl())
                .products(categoryDTO.getProducts())
                .build();
        categoryRepository.save(newCategory);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, CategoryDTO categoryDTO) {
        Category categoryToBeUpdated = findCategoryById(id);
        boolean isCategoryNameExist = categoryRepository.findAll().stream()
                .anyMatch(category -> !Objects.equals(category.getId(), categoryToBeUpdated.getId()) &&
                        category.getCategoryName().equals(categoryDTO.getCategoryName()));
        if (isCategoryNameExist) throw new CategoryIsAlreadyExistException(UNSUCCESSFUL_CATEGORY_UPDATE_MESSAGE);

        categoryToBeUpdated.setCategoryName(categoryDTO.getCategoryName());
        categoryToBeUpdated.setDescription(categoryDTO.getDescription());
        categoryToBeUpdated.setImageUrl(categoryDTO.getImageUrl());
        categoryToBeUpdated.setProducts(categoryDTO.getProducts());
        categoryRepository.save(categoryToBeUpdated);
        return new ResponseEntity<Category>(categoryToBeUpdated, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> deleteCategoryById(Long id) {
        Category categoryToBeDeleted = findCategoryById(id);
        categoryRepository.deleteById(id);
        return new ResponseEntity<Category>(categoryToBeDeleted, HttpStatus.OK);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_MESSAGE + id));
    }

    @Override
    public Category findCategoryByCategoryName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow(
                        () -> new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_BY_NAME_MESSAGE + categoryName));
    }

    @Override
    public boolean IsCategoryExistByName(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}
