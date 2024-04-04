package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="item_file_table")
public class ItemFileEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //ItemEntity idx 값 가져오기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_idx")
    private ItemEntity itemEntity;

    @Column
    private String storedFileName;

    @Column
    private String originalFileName;

    public static ItemFileEntity toMainFileEntity(ItemEntity itemEntity, String originalFileName, String storedFileName){
        ItemFileEntity itemFileEntity = new ItemFileEntity();
        itemFileEntity.setOriginalFileName(originalFileName);
        itemFileEntity.setStoredFileName(storedFileName);
        itemFileEntity.setItemEntity(itemEntity);
        return itemFileEntity;
    }
    public static ItemFileEntity toItemFileEntity(ItemEntity itemEntity, String originalFileName1, String storedFileName1){
        ItemFileEntity itemFileEntity = new ItemFileEntity();
        itemFileEntity.setOriginalFileName(originalFileName1);
        itemFileEntity.setStoredFileName(storedFileName1);
        itemFileEntity.setItemEntity(itemEntity);
        return itemFileEntity;
    }
}
