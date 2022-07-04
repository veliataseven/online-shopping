package com.example.online.shopping.repositories;

import com.example.online.shopping.models.Category;
import com.example.online.shopping.models.DigitalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProduct, Long> {

    boolean existsByProductName(String productName);

    List<DigitalProduct> findByCategory(Category category);

    // List<DigitalProduct> findByCategoryName(String categoryName);
}
