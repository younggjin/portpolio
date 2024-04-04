package com.example.portfolio.repository;

import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.entity.ItemFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemFileRepository extends JpaRepository<ItemFileEntity, Long> {
    List<ItemFileEntity> findByItemEntity_Idx(Long idx);
}
