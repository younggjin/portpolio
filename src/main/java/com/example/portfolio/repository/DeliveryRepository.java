package com.example.portfolio.repository;

import com.example.portfolio.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Principal;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    List<DeliveryEntity> findByUserid(String userid);
}
