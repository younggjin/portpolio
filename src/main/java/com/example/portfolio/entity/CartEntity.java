package com.example.portfolio.entity;

import com.example.portfolio.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart_table")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Setter
    private String userid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_idx")
    @Setter
    private ItemEntity itemEntity;


    @Column
    @Setter
    private int quantity;


    public static CartEntity toCartEntity(CartDTO cartDTO, Principal principal, ItemEntity itemEntity){
        CartEntity cartEntity = new CartEntity();

        cartEntity.setUserid(principal.getName());
        cartEntity.setItemEntity(itemEntity);

        cartEntity.setQuantity(cartDTO.getItem_quantity());


        return cartEntity;
    }

}
