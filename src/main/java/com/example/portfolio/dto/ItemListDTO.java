package com.example.portfolio.dto;

import com.example.portfolio.entity.ItemEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemListDTO {

    private Long idx;
    private String item_name;
    private int item_price;
    private int item_quantity;
    private LocalDateTime regdate;
    private String storedFileName;


    public static ItemListDTO toItemListDTO(ItemEntity item) {
        ItemListDTO itemListDTO = new ItemListDTO();
        itemListDTO.setIdx(item.getIdx());
        itemListDTO.setItem_name(item.getItem_name());
        itemListDTO.setItem_price(item.getItem_price());
        itemListDTO.setItem_quantity(item.getItem_quantity());
        itemListDTO.setRegdate(item.getRegdate());
        itemListDTO.setStoredFileName(item.getItemFileEntityList().get(0).getStoredFileName());

        return itemListDTO;
    }
}
