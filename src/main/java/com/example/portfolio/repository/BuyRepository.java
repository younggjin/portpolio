package com.example.portfolio.repository;

import com.example.portfolio.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyRepository extends JpaRepository<BuyEntity, Long> {
    BuyEntity findByUserid(String userid);
}
