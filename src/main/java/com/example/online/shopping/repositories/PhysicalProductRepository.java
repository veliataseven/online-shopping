package com.example.online.shopping.repositories;

import com.example.online.shopping.models.Category;
import com.example.online.shopping.models.PhysicalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProduct, Long> {

    boolean existsByProductName(String productName);

    List<PhysicalProduct> findByCategory(Category category);
}
