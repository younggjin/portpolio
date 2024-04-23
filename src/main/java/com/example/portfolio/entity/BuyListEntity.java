package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="buyList_table")
public class BuyListEntity extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    @Setter
    private String userid;

    @OneToMany(mappedBy = "buyListEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DeliveryEntity> deliveryEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "buyListEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BuyItemEntity> buyItemEntityList = new ArrayList<>();

    public static BuyListEntity buyListEntity(Principal principal){
        BuyListEntity buyListEntity = new BuyListEntity();

        buyListEntity.setUserid(principal.getName());

        return buyListEntity;
    }
}
