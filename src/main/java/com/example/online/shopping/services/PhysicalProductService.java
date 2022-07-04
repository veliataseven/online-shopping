package com.example.online.shopping.services;

import com.example.online.shopping.dtos.PhysicalProduct.PhysicalProductDTO;
import com.example.online.shopping.enums.ShippingCategory;
import com.example.online.shopping.exceptions.Category.CategoryNotFoundException;
import com.example.online.shopping.exceptions.PhysicalProduct.NoPhysicalProductFoundInListException;
import com.example.online.shopping.exceptions.PhysicalProduct.PhysicalProductIsAlreadyExistException;
import com.example.online.shopping.exceptions.PhysicalProduct.PhysicalProductNotFoundException;
import com.example.online.shopping.models.Category;
import com.example.online.shopping.models.PhysicalProduct;
import com.example.online.shopping.repositories.PhysicalProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
public class PhysicalProductService implements PhysicalProductServiceInterface {

    private final PhysicalProductRepository physicalProductRepository;
    private final CategoryService categoryService;

    public PhysicalProductService(
            PhysicalProductRepository physicalProductRepository, CategoryService categoryService) {
        this.physicalProductRepository = physicalProductRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<PhysicalProduct>> getAllPhysicalProducts() {
        List<PhysicalProduct> physicalProducts = physicalProductRepository.findAll();
        if (physicalProducts.isEmpty())
            throw new NoPhysicalProductFoundInListException(UNSUCCESSFUL_GET_ALL_PHYSICAL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<PhysicalProduct>>(physicalProducts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PhysicalProduct>> getPhysicalProductsByCategoryName(String categoryName) {
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        List<PhysicalProduct> physicalProducts = physicalProductRepository.findByCategory(category);
        if (physicalProducts.isEmpty())
            throw new NoPhysicalProductFoundInListException(UNSUCCESSFUL_GET_ALL_PHYSICAL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<PhysicalProduct>>(physicalProducts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhysicalProduct> getPhysicalProductById(Long id) {
        PhysicalProduct physicalProduct = findPhysicalProductById(id);
        return new ResponseEntity<PhysicalProduct>(physicalProduct, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhysicalProduct> createPhysicalProduct(PhysicalProductDTO physicalProductDTO) {
        boolean IsPhysicalProductExistByName = IsPhysicalProductExistByName(physicalProductDTO.getProductName());
        if (IsPhysicalProductExistByName)
            throw new PhysicalProductIsAlreadyExistException(UNSUCCESSFUL_PHYSICAL_PRODUCT_CREATE_MESSAGE);
        boolean IsCategoryExistByCategoryName = categoryService
                .IsCategoryExistByName(physicalProductDTO.getCategoryName());
        if (!IsCategoryExistByCategoryName)
            throw new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_BY_NAME_MESSAGE);

        Category category = categoryService.findCategoryByCategoryName(physicalProductDTO.getCategoryName());
        PhysicalProduct newPhysicalProduct = PhysicalProduct.builder()
                .productName(physicalProductDTO.getProductName())
                .category(category)
                .basePrice(physicalProductDTO.getBasePrice())
                .description(physicalProductDTO.getDescription())
                .productDiscount(physicalProductDTO.getProductDiscount())
                .imageUrl(physicalProductDTO.getImageUrl())
                .weight(physicalProductDTO.getWeight())
                .shippingCategory(ShippingCategory.valueOf(physicalProductDTO.getShippingCategory()))
                .build();
        physicalProductRepository.save(newPhysicalProduct);
        return new ResponseEntity<PhysicalProduct>(newPhysicalProduct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PhysicalProduct> updatePhysicalProduct(Long id, PhysicalProductDTO physicalProductDTO) {
        boolean IsPhysicalProductExistById = IsPhysicalProductExistById(id);
        if (!IsPhysicalProductExistById)
            throw new PhysicalProductNotFoundException(UNSUCCESSFUL_GET_PHYSICAL_PRODUCT_MESSAGE);
        boolean IsCategoryExistByCategoryName = categoryService
                .IsCategoryExistByName(physicalProductDTO.getCategoryName());
        if (!IsCategoryExistByCategoryName)
            throw new CategoryNotFoundException(UNSUCCESSFUL_GET_CATEGORY_BY_NAME_MESSAGE);

        Category category = categoryService.findCategoryByCategoryName(physicalProductDTO.getCategoryName());
        PhysicalProduct physicalProductToBeUpdated = findPhysicalProductById(id);

        physicalProductToBeUpdated.setProductName(physicalProductDTO.getProductName());
        physicalProductToBeUpdated.setCategory(category);
        physicalProductToBeUpdated.setBasePrice(physicalProductDTO.getBasePrice());
        physicalProductToBeUpdated.setDescription(physicalProductDTO.getDescription());
        physicalProductToBeUpdated.setProductDiscount(physicalProductDTO.getProductDiscount());
        physicalProductToBeUpdated.setImageUrl(physicalProductDTO.getImageUrl());
        physicalProductToBeUpdated.setWeight(physicalProductDTO.getWeight());
        physicalProductToBeUpdated.setShippingCategory(
                ShippingCategory.valueOf(physicalProductDTO.getShippingCategory()));
        physicalProductRepository.save(physicalProductToBeUpdated);
        return new ResponseEntity<PhysicalProduct>(physicalProductToBeUpdated, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PhysicalProduct> deletePhysicalProduct(Long id) {
        PhysicalProduct physicalProductToBeDeleted = findPhysicalProductById(id);
        physicalProductRepository.deleteById(id);
        return new ResponseEntity<PhysicalProduct>(physicalProductToBeDeleted, HttpStatus.OK);
    }

    @Override
    public PhysicalProduct findPhysicalProductById(Long id) {
        return physicalProductRepository.findById(id)
                .orElseThrow(() -> new PhysicalProductNotFoundException(UNSUCCESSFUL_GET_PHYSICAL_PRODUCT_MESSAGE));
    }

    @Override
    public boolean IsPhysicalProductExistById(Long id) {
        return physicalProductRepository.existsById(id);
    }

    @Override
    public boolean IsPhysicalProductExistByName(String productName) {
        return physicalProductRepository.existsByProductName(productName);
    }
}
