package com.example.portfolio.entity;

import com.example.portfolio.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="item_table")
public class ItemEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //ItemCategorizeEntity에 idx 연결
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="categorize_idx")
    private ItemCategoryEntity ItemCategoryEntity;

    @Column
    private String category_idx;

    @Column
    private String item_name;

    @Column
    private int item_price;

    @Column
    private int item_quantity;

    @Column
    private String item_content;

    @Column
    private int fileAttached;

    //ItemFileEntity에 item_idx
    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.REMOVE, orphanRemoval = true ,fetch = FetchType.EAGER)
    private List<ItemFileEntity> itemFileEntityList = new ArrayList<>();

    public ItemEntity(Long idx, String category_idx, String item_name, int item_price, int item_quantity, String item_content, int fileAttached){
        this.idx = idx;
        this.category_idx = category_idx;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_content = item_content;
        this.fileAttached = fileAttached;
    }

    public static ItemEntity toSaveEntity(ItemDTO itemDTO){
        ItemEntity itemEntity = new ItemEntity();

//        String option1Value = itemDTO.getOption1();
//        String option2Value = itemDTO.getOption2();
//
//        itemEntity.setIdx(itemDTO.getIdx());
//        if (option1Value != null) {
//            itemEntity.setCategory_idx(itemDTO.getOption1());
//        }else if (option2Value != null) {
//            itemEntity.setCategory_idx(itemDTO.getOption2());
//        }
        itemEntity.setIdx(itemDTO.getIdx());
        itemEntity.setItem_name(itemDTO.getItem_name());
        itemEntity.setItem_price(itemDTO.getItem_price());
        itemEntity.setItem_quantity(itemDTO.getItem_quantity());
        itemEntity.setItem_content(itemDTO.getItem_content());
        itemEntity.setFileAttached(0);

        return itemEntity;
    }

    public static ItemEntity toSaveFileEntity(ItemDTO itemDTO){
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setIdx(itemDTO.getIdx());
        itemEntity.setItem_name(itemDTO.getItem_name());
        itemEntity.setItem_price(itemDTO.getItem_price());
        itemEntity.setItem_quantity(itemDTO.getItem_quantity());
        itemEntity.setItem_content(itemDTO.getItem_content());
        itemEntity.setFileAttached(1);

        return itemEntity;
    }

}
