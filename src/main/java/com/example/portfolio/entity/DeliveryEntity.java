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
@Table(name="delivery_table")
public class DeliveryEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Setter
    private String userid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buy_idx") // 외래 키(Foreign Key)를 buy_table의 idx와 매핑
    @Setter
    private BuyListEntity buyListEntity;

    @Column
    @Setter
    private String deliveryName;

    @Column
    @Setter
    private String deliveryAddress;

    @Column
    @Setter
    private String deliveryTel;

    @Column
    @Setter
    private String deliveryContent;

    public static DeliveryEntity saveDeliveryEntity(DeliveryDTO deliveryDTO, Principal principal, BuyListEntity buyListEntity){
        String address = deliveryDTO.getAdd1()+"@"+deliveryDTO.getAdd2()+"@"+deliveryDTO.getAdd3();
        String tel = deliveryDTO.getTel1()+"-"+deliveryDTO.getTel2()+"-"+deliveryDTO.getTel3();


        DeliveryEntity deliveryEntity = new DeliveryEntity();

        deliveryEntity.setUserid(principal.getName());
        deliveryEntity.setBuyListEntity(buyListEntity);
        deliveryEntity.setDeliveryName(deliveryDTO.getDeliName());
        deliveryEntity.setDeliveryAddress(address);
        deliveryEntity.setDeliveryTel(tel);
        deliveryEntity.setDeliveryContent(deliveryDTO.getDeliveryContent());

        return deliveryEntity;
    }
}
