package com.example.portfolio.repository;

import com.example.portfolio.entity.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long>
{
    @Query(value ="SELECT * FROM item_category_table WHERE pidx2 = 0 AND pidx3 = 0 ORDER BY pidx1 DESC", nativeQuery = true)
    List<ItemCategoryEntity> findFirst();

    @Query(value = "SELECT * FROM item_category_table WHERE pidx1 = ?1 AND pidx2 != 0 AND pidx3 = 0 ORDER BY pidx2 DESC", nativeQuery = true)
    List<ItemCategoryEntity> findSecond(String category1);

    @Query(value = "SELECT * FROM item_category_table WHERE pidx1 = ?1 AND pidx2 = ?2 AND pidx3 != 0 ORDER BY pidx3 DESC", nativeQuery = true)
    List<ItemCategoryEntity> findThird(String category1, String category2);

    @Query(value = "SELECT * FROM item_category_table WHERE pidx2 = 0 and pidx3 = 0 ORDER BY category_name ASC", nativeQuery = true)
    List<ItemCategoryEntity> findFirstName();

    @Query(value = "SELECT * FROM item_category_table WHERE pidx1 = ?1 AND pidx2 != 0 AND pidx3 = 0 ORDER BY category_name ASC", nativeQuery = true)
    List<ItemCategoryEntity> findSecondName(String category1);

    @Query(value = "SELECT * FROM item_category_table WHERE pidx1 = ?1 AND pidx2 = ?2 AND pidx3 != 0 ORDER BY category_name ASC", nativeQuery = true)
    List<ItemCategoryEntity> findThirdName(String category1, String category2);

    @Query(value = "SELECT * FROM item_category_table WHERE pidx3 = 0", nativeQuery = true)
    List<ItemCategoryEntity> findSecondCategory();
}
