package com.example.portfolio.entity;

import com.example.portfolio.dto.DeliveryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="buyItem_table")
public class BuyItemEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyIdx")
    @Setter
    private BuyListEntity buyListEntity;

    @Column
    @Setter
    private String userid;

    @Column
    @Setter
    private String itemIdx;

    @Column
    @Setter
    private String itemPrice;

    @Column
    @Setter
    private String quantity;

    @Column
    @Setter
    private String totalPrice;


    public static BuyItemEntity saveBuyEntity(DeliveryDTO deliveryDTO, Principal principal, BuyListEntity buyListEntity){

        BuyItemEntity buyItemEntity = new BuyItemEntity();
        
        for(int a=0; a< deliveryDTO.getItemIdx().size(); a++) {
            buyItemEntity.setBuyListEntity(buyListEntity);
            buyItemEntity.setUserid(principal.getName());
            buyItemEntity.setItemIdx(deliveryDTO.getItemIdx().get(a));
            buyItemEntity.setItemPrice(deliveryDTO.getItemPrice().get(a));
            buyItemEntity.setQuantity(deliveryDTO.getQuantity().get(a));
            buyItemEntity.setTotalPrice(deliveryDTO.getTotalPrice().get(a));
        }
        return buyItemEntity;
    }

}
