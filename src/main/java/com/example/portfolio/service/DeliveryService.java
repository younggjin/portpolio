package com.example.portfolio.service;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.dto.DeliveryDTO;
import com.example.portfolio.dto.MemberDTO;
//import com.example.portfolio.entity.BuyEntity;
import com.example.portfolio.entity.*;
//import com.example.portfolio.entity.DeliveryEntity;
import com.example.portfolio.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Log4j2
public class DeliveryService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;
    private final BuyItemRepository buyItemRepository;
    private final ItemRepository itemRepository;
    private final BuyListRepository buyListRepository;

    public DeliveryService(MemberRepository memberRepository, CartRepository cartRepository, DeliveryRepository deliveryRepository, BuyItemRepository buyItemRepository, ItemRepository itemRepository, BuyListRepository buyListRepository) {
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
        this.deliveryRepository = deliveryRepository;
        this.buyItemRepository = buyItemRepository;
        this.itemRepository = itemRepository;
        this.buyListRepository = buyListRepository;
    }


    public MemberDTO findMember(Principal principal){

        MemberEntity memberEntity = memberRepository.findByUserid(principal.getName()).orElse(null);

        if (memberEntity != null) {
            return MemberDTO.toMemberDTO(memberEntity);
        }else{
            return null;

        }
    }
    public CartDTO findCart(Long cartIdx){
        CartEntity cart = cartRepository.findById(cartIdx).orElse(null);
        if(cart != null){
            return CartDTO.tocartDTO(cart);
        }else{
            return null;
        }
    }
    public void saveDelivery(DeliveryDTO deliveryDTO, Principal principal){
        BuyListEntity buyListEntity = buyListRepository.save(BuyListEntity.buyListEntity(principal));

        BuyListEntity buyListEntity1 = buyListRepository.findById(buyListEntity.getIdx()).orElse(null);

        buyItemRepository.save(BuyItemEntity.saveBuyEntity(deliveryDTO, principal, buyListEntity1));
        deliveryRepository.save(DeliveryEntity.saveDeliveryEntity(deliveryDTO, principal, buyListEntity1));

        for(int a=0; a<deliveryDTO.getCartIdx().size(); a++){

            ItemEntity itemEntity = itemRepository.findById(Long.valueOf(deliveryDTO.getItemIdx().get(a))).orElse(null);
            int itemQuantity = 0;
            if (itemEntity != null) {
                itemQuantity = itemEntity.getItem_quantity();
            }
            int deliQuantity = Integer.parseInt(deliveryDTO.getQuantity().get(a));
            int i = itemQuantity - deliQuantity;
            if (itemEntity != null) {
                itemEntity.setItem_quantity(i);
            }
            if (itemEntity != null) {
                itemRepository.save(itemEntity);
            }

            Long delCart = Long.valueOf(deliveryDTO.getCartIdx().get(a));
            cartRepository.deleteById(delCart);
        }
    }


    public DeliveryDTO findDelivery(Principal principal){

        DeliveryEntity deliveryEntity = deliveryRepository.findByUserid(principal.getName()).get(0);



        return null;
    }
}
