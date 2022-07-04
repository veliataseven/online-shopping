package com.example.online.shopping.repositories;

import com.example.online.shopping.models.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
}
