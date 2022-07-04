package com.example.online.shopping.repositories;

import com.example.online.shopping.models.EftAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EftAccountRepository extends JpaRepository<EftAccount, Long> {
}
