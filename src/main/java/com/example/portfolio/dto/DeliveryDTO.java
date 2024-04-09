package com.example.portfolio.dto;

import lombok.*;
import org.hibernate.type.internal.ParameterizedTypeImpl;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

    private Long idx;

    //배송 받는 사람 정보
    private String deliName;
    private String tel1;
    private String tel2;
    private String tel3;
    private String add1;
    private String add2;
    private String add3;
    private String deliveryContent;

    //상품 내역
    private List<String> itemIdx;
    private List<String> itemPrice;
    private List<String> quantity;
    private List<String> totalPrice;

    private LocalDateTime regDate;


}
