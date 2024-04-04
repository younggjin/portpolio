package com.example.portfolio.dto;

import com.example.portfolio.entity.ItemCategoryEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDTO {

    private Long idx;

    private int pidx1;
    private int pidx2;
    private int pidx3;

    private String categoryname;

    public static ItemCategoryDTO itemCategoryDTO(ItemCategoryEntity itemCategoryEntity){
        ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
        itemCategoryDTO.setIdx(itemCategoryEntity.getIdx());
        itemCategoryDTO.setPidx1(itemCategoryEntity.getPidx1());
        itemCategoryDTO.setPidx2(itemCategoryEntity.getPidx2());
        itemCategoryDTO.setPidx3(itemCategoryEntity.getPidx3());
        itemCategoryDTO.setCategoryname(itemCategoryEntity.getCategory_name());

        return itemCategoryDTO;
    }
}
