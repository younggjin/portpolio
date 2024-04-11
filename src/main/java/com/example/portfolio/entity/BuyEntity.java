package com.example.portfolio.entity;

import com.example.portfolio.dto.DeliveryDTO;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="buy_table")
public class BuyEntity extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

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


    public static BuyEntity saveBuyEntity(DeliveryDTO deliveryDTO, Principal principal){

        BuyEntity buyEntity = new BuyEntity();
        for(int a=0; a< deliveryDTO.getItemIdx().size(); a++) {
            buyEntity.setUserid(principal.getName());
            buyEntity.setItemIdx(deliveryDTO.getItemIdx().get(a));
            buyEntity.setItemPrice(deliveryDTO.getItemPrice().get(a));
            buyEntity.setQuantity(deliveryDTO.getQuantity().get(a));
            buyEntity.setTotalPrice(deliveryDTO.getTotalPrice().get(a));
        }
        return buyEntity;
    }

}
