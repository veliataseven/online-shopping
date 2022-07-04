package com.example.online.shopping.services;

import com.example.online.shopping.dtos.PhysicalProduct.PhysicalProductDTO;
import com.example.online.shopping.models.PhysicalProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhysicalProductServiceInterface {

    ResponseEntity<List<PhysicalProduct>> getAllPhysicalProducts();

    ResponseEntity<List<PhysicalProduct>> getPhysicalProductsByCategoryName(String categoryName);

    ResponseEntity<PhysicalProduct> getPhysicalProductById(Long id);

    ResponseEntity<PhysicalProduct> createPhysicalProduct(PhysicalProductDTO physicalProductDTO);

    ResponseEntity<PhysicalProduct> updatePhysicalProduct(Long id, PhysicalProductDTO physicalProductDTO);

    ResponseEntity<PhysicalProduct> deletePhysicalProduct(Long id);

    PhysicalProduct findPhysicalProductById(Long id);

    boolean IsPhysicalProductExistById(Long id);

    boolean IsPhysicalProductExistByName(String productName);
}
