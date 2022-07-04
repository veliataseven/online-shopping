package com.example.online.shopping.services;

import com.example.online.shopping.dtos.DigitalProduct.DigitalProductDTO;
import com.example.online.shopping.exceptions.Category.CategoryNotFoundException;
import com.example.online.shopping.exceptions.DigitalProduct.DigitalProductIsAlreadyExistException;
import com.example.online.shopping.exceptions.DigitalProduct.DigitalProductNotFoundException;
import com.example.online.shopping.exceptions.DigitalProduct.NoDigitalProductFoundInListException;
import com.example.online.shopping.models.Category;
import com.example.online.shopping.models.DigitalProduct;
import com.example.online.shopping.repositories.DigitalProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
public class DigitalProductService implements DigitalProductServiceInterface {

    private final DigitalProductRepository digitalProductRepository;
    private final CategoryService categoryService;

    public DigitalProductService(DigitalProductRepository digitalProductRepository, CategoryService categoryService) {
        this.digitalProductRepository = digitalProductRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<DigitalProduct>> getAllDigitalProducts() {
        List<DigitalProduct> digitalProducts = digitalProductRepository.findAll();
        if (digitalProducts.isEmpty())
            throw new NoDigitalProductFoundInListException(UNSUCCESSFUL_GET_ALL_DIGITAL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<DigitalProduct>>(digitalProducts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DigitalProduct>> getDigitalProductsByCategoryName(String categoryName) {
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        List<DigitalProduct> digitalProducts = digitalProductRepository.findByCategory(category);
        if (digitalProducts.isEmpty())
            throw new NoDigitalProductFoundInListException(UNSUCCESSFUL_GET_ALL_DIGITAL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<DigitalProduct>>(digitalProducts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DigitalProduct> getDigitalProductById(Long id) {
        DigitalProduct digitalProduct = findDigitalProductById(id);
        return new ResponseEntity<DigitalProduct>(digitalProduct, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DigitalProduct> createDigitalProduct(DigitalProductDTO digitalProductDTO) {
        boolean IsDigitalProductExistByName = IsDigitalProductExistByName(digitalProductDTO.getProductName());
        if (IsDigitalProductExistByName)
            throw new DigitalProductIsAlreadyExistException(UNSUCCESSFUL_DIGITAL_PRODUCT_CREATE_MESSAGE);
        boolean IsCategoryExistByCategoryName = categoryService.IsCategoryExistByName(digitalProductDTO.getCategoryName());
        if (!IsCategoryExistByCategoryName)
            throw new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_BY_NAME_MESSAGE);

        Category category = categoryService.findCategoryByCategoryName(digitalProductDTO.getCategoryName());
        DigitalProduct newDigitalProduct = DigitalProduct.builder()
                .productName(digitalProductDTO.getProductName())
                .category(category)
                .basePrice(digitalProductDTO.getBasePrice())
                .description(digitalProductDTO.getDescription())
                .productDiscount(digitalProductDTO.getProductDiscount())
                .imageUrl(digitalProductDTO.getImageUrl())
                .build();
        digitalProductRepository.save(newDigitalProduct);
        return new ResponseEntity<DigitalProduct>(newDigitalProduct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DigitalProduct> updateDigitalProduct(Long id, DigitalProductDTO digitalProductDTO) {
        boolean IsDigitalProductExistById = IsDigitalProductExistById(id);
        if (!IsDigitalProductExistById)
            throw new DigitalProductNotFoundException(UNSUCCESSFUL_GET_DIGITAL_PRODUCT_MESSAGE);
        boolean IsCategoryExistByCategoryName = categoryService.IsCategoryExistByName(digitalProductDTO.getCategoryName());
        if (!IsCategoryExistByCategoryName)
            throw new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_BY_NAME_MESSAGE);

        Category category = categoryService.findCategoryByCategoryName(digitalProductDTO.getCategoryName());
        DigitalProduct digitalProductToBeUpdated = findDigitalProductById(id);

        digitalProductToBeUpdated.setProductName(digitalProductDTO.getProductName());
        digitalProductToBeUpdated.setCategory(category);
        digitalProductToBeUpdated.setBasePrice(digitalProductDTO.getBasePrice());
        digitalProductToBeUpdated.setDescription(digitalProductDTO.getDescription());
        digitalProductToBeUpdated.setProductDiscount(digitalProductDTO.getProductDiscount());
        digitalProductToBeUpdated.setImageUrl(digitalProductDTO.getImageUrl());
        digitalProductRepository.save(digitalProductToBeUpdated);
        return new ResponseEntity<DigitalProduct>(digitalProductToBeUpdated, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DigitalProduct> deleteDigitalProduct(Long id) {
        DigitalProduct digitalProductToBeDeleted = findDigitalProductById(id);
        digitalProductRepository.deleteById(id);
        return new ResponseEntity<DigitalProduct>(digitalProductToBeDeleted, HttpStatus.OK);
    }

    @Override
    public DigitalProduct findDigitalProductById(Long id) {
        return digitalProductRepository.findById(id)
                .orElseThrow(() -> new DigitalProductNotFoundException(UNSUCCESSFUL_GET_DIGITAL_PRODUCT_MESSAGE));
    }

    @Override
    public boolean IsDigitalProductExistById(Long id) {
        return digitalProductRepository.existsById(id);
    }

    @Override
    public boolean IsDigitalProductExistByName(String productName) {
        return digitalProductRepository.existsByProductName(productName);
    }
}
