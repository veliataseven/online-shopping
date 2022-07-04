package com.example.online.shopping.services;

import com.example.online.shopping.dtos.DigitalProduct.DigitalProductDTO;
import com.example.online.shopping.models.DigitalProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DigitalProductServiceInterface {

    public ResponseEntity<List<DigitalProduct>> getAllDigitalProducts();

    public ResponseEntity<DigitalProduct> getDigitalProductById(Long id);

    public ResponseEntity<DigitalProduct> createDigitalProduct(DigitalProductDTO digitalProductDTO);

    public ResponseEntity<DigitalProduct> updateDigitalProduct(Long id, DigitalProductDTO digitalProductDTO);

    public ResponseEntity<DigitalProduct> deleteDigitalProduct(Long id);

    public ResponseEntity<List<DigitalProduct>> getDigitalProductsByCategoryName(String categoryName);

    public DigitalProduct findDigitalProductById(Long id);

    public boolean IsDigitalProductExistById(Long id);

    public boolean IsDigitalProductExistByName(String digitalProductName);
}
