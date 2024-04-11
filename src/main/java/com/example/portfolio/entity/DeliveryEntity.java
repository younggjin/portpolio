package com.example.portfolio.entity;

import com.example.portfolio.dto.DeliveryDTO;
import com.example.portfolio.repository.BuyRepository;
import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.time.LocalDateTime;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyIdx")
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

    public static DeliveryEntity saveDeliveryEntity(DeliveryDTO deliveryDTO, Principal principal, BuyEntity buyEntity){
        String address = deliveryDTO.getAdd1()+"@"+deliveryDTO.getAdd2()+"@"+deliveryDTO.getAdd3();
        String tel = deliveryDTO.getTel1()+"-"+deliveryDTO.getTel2()+"-"+deliveryDTO.getTel3();


        DeliveryEntity deliveryEntity = new DeliveryEntity();

        deliveryEntity.setUserid(principal.getName());
        deliveryEntity.setBuyEntity(buyEntity);
        deliveryEntity.setDeliveryName(deliveryDTO.getDeliName());
        deliveryEntity.setDeliveryAddress(address);
        deliveryEntity.setDeliveryTel(tel);
        deliveryEntity.setDeliveryContent(deliveryDTO.getDeliveryContent());

        return deliveryEntity;
    }
}
