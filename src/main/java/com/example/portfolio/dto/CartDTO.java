package com.example.portfolio.dto;

import com.example.portfolio.entity.CartEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long idx;
    private Long item_idx;
    private int item_quantity;

    private String userid;
    private String item_name;
    private int item_price;

    private String storedFileName;

    public static CartDTO tocartDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();

        cartDTO.setIdx(cartEntity.getIdx());
        cartDTO.setUserid(cartEntity.getUserid());
        cartDTO.setItem_idx(cartEntity.getItemEntity().getIdx());
        cartDTO.setStoredFileName(cartEntity.getItemEntity().getItemFileEntityList().get(0).getStoredFileName());
        cartDTO.setItem_name(cartEntity.getItemEntity().getItem_name());
        cartDTO.setItem_quantity(cartEntity.getQuantity());
        cartDTO.setItem_price(cartEntity.getItemEntity().getItem_price());

        return cartDTO;
    }


}
