package com.example.online.shopping.services;

import com.example.online.shopping.dtos.Response.ResponseDTO;
import com.example.online.shopping.exceptions.Product.NoProductFoundInListException;
import com.example.online.shopping.exceptions.Product.ProductNotFoundException;
import com.example.online.shopping.models.Category;
import com.example.online.shopping.models.Product;
import com.example.online.shopping.repositories.CategoryRepository;
import com.example.online.shopping.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
public class ProductService implements ProductServiceInterface{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) throw new NoProductFoundInListException(UNSUCCESSFUL_GET_ALL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategoryName(String categoryName) {
        Category category = categoryService.findCategoryByCategoryName(categoryName);
        List<Product> products = productRepository.findByCategory(category);
        if(products.isEmpty()) throw new NoProductFoundInListException(UNSUCCESSFUL_GET_ALL_PRODUCTS_MESSAGE);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Product product = findProductById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> deleteProduct(Long id) {
        Product productToBeDeleted = findProductById(id);
        productRepository.deleteById(id);
        return new ResponseEntity<Product>(productToBeDeleted, HttpStatus.OK);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(UNSUCCESSFUL_GET_PRODUCT_MESSAGE));
    }
}
