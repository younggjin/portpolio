package com.example.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="buy_table")
public class BuyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userid;

    @OneToOne
    private DeliveryEntity deliveryEntity;

    @Column
    private String itemIdx;

    @Column
    private int itemPrice;

    @Column
    private int quantity;

    @Column
    private int totalPrice;

    @Column
    private LocalDateTime regDate;
}
