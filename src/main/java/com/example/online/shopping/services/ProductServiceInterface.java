package com.example.online.shopping.services;

import com.example.online.shopping.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServiceInterface {

    ResponseEntity<List<Product>> getAllProducts();

    ResponseEntity<Product> getProductById(Long id);

    ResponseEntity<List<Product>> getProductsByCategoryName(String categoryName);

    ResponseEntity<Product> deleteProduct(Long id);

    Product findProductById(Long id);
}
