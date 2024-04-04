package com.example.portfolio.entity;

import com.example.portfolio.dto.ItemCategoryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="item_category_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pidx1", "pidx2", "pidx3"})})
public class ItemCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Setter
    private int pidx1;

    @Column
    @Setter
    private int pidx2;

    @Column
    @Setter
    private int pidx3;

    @Column
    @Setter
    private String category_name;

    //ItemEntity에 categorize_idx
    @OneToMany(mappedBy = "ItemCategoryEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemEntity> itemEntityList;

    public static ItemCategoryEntity itemCategoryEntity(ItemCategoryDTO itemCategoryDTO){
        ItemCategoryEntity itemCategoryEntitys = new ItemCategoryEntity();
        itemCategoryEntitys.setPidx1(itemCategoryDTO.getPidx1());
        itemCategoryEntitys.setPidx2(itemCategoryDTO.getPidx2());
        itemCategoryEntitys.setPidx3(itemCategoryDTO.getPidx3());
        itemCategoryEntitys.setCategory_name(itemCategoryDTO.getCategoryname());

        return itemCategoryEntitys;

    }

}
