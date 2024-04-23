package com.example.portfolio.dto;

import com.example.portfolio.entity.BuyItemEntity;
import com.example.portfolio.entity.DeliveryEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
    private List<String> cartIdx;
    private List<String> itemIdx;
    private List<String> itemPrice;
    private List<String> quantity;
    private List<String> totalPrice;

    private LocalDateTime regDate;

    public static DeliveryDTO toDeliveryDTO(DeliveryEntity deliveryEntity){
        DeliveryDTO deliveryDTO = new DeliveryDTO();

        deliveryDTO.setDeliName(deliveryEntity.getDeliveryName());

        deliveryDTO.setTel1(deliveryEntity.getDeliveryTel().split("-")[0]);
        deliveryDTO.setTel2(deliveryEntity.getDeliveryTel().split("-")[1]);
        deliveryDTO.setTel3(deliveryEntity.getDeliveryTel().split("-")[2]);

        deliveryDTO.setAdd1(deliveryEntity.getDeliveryAddress().split("@")[0]);
        deliveryDTO.setAdd2(deliveryEntity.getDeliveryAddress().split("@")[0]);
        deliveryDTO.setAdd3(deliveryEntity.getDeliveryAddress().split("@")[0]);

        deliveryDTO.setDeliveryContent(deliveryEntity.getDeliveryContent());


        return deliveryDTO;
    }

    public static DeliveryDTO toBuyDTO(BuyItemEntity buyItemEntity){
        DeliveryDTO deliveryDTO = new DeliveryDTO();



        return deliveryDTO;
    }

}
