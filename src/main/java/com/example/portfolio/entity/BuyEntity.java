//package com.example.portfolio.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name="buy_table")
//public class BuyEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idx;
//
//    @Column
//    private String mem_idx;
//
//    @Column
//    private String item_idx;
//
//    @Column
//    private int item_price;
//
//    @Column
//    private String quantity;
//
//    @Column
//    private int total_price;
//
//    @Column
//    private LocalDateTime regdate;
//}
