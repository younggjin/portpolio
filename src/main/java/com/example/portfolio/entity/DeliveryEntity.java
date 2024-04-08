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
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Setter
    private String userid;

    @OneToOne
    @Setter
    private BuyEntity buyEntity;

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

    public static DeliveryEntity deliveryEntity(DeliveryDTO deliveryDTO, Principal principal){
        String address = deliveryDTO.getAdd1()+"@"+deliveryDTO.getAdd2()+"@"+deliveryDTO.getAdd3();
        String tel = deliveryDTO.getTel1()+"-"+deliveryDTO.getTel2()+"-"+deliveryDTO.getTel3();

        DeliveryEntity deliveryEntity = new DeliveryEntity();

        deliveryEntity.setUserid(principal.getName());
        deliveryEntity.setDeliveryName(deliveryDTO.getDelName());
        deliveryEntity.setDeliveryAddress(address);
        deliveryEntity.setDeliveryTel(tel);
        deliveryEntity.setDeliveryContent(deliveryDTO.getDeliveryContent());


        return deliveryEntity;
    }
}
