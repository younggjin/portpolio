package com.example.portfolio.service;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.dto.DeliveryDTO;
import com.example.portfolio.dto.MemberDTO;
//import com.example.portfolio.entity.BuyEntity;
import com.example.portfolio.entity.BuyEntity;
import com.example.portfolio.entity.CartEntity;
//import com.example.portfolio.entity.DeliveryEntity;
import com.example.portfolio.entity.DeliveryEntity;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.BuyRepository;
import com.example.portfolio.repository.CartRepository;
import com.example.portfolio.repository.DeliveryRepository;
import com.example.portfolio.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class DeliveryService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final DeliveryRepository deliveryRepository;
    private final BuyRepository buyRepository;

    public DeliveryService(MemberRepository memberRepository, CartRepository cartRepository, DeliveryRepository deliveryRepository, BuyRepository buyRepository) {
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
        this.deliveryRepository = deliveryRepository;
        this.buyRepository = buyRepository;
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
    public void saveBuy(DeliveryDTO deliveryDTO, Principal principal){
        buyRepository.save(BuyEntity.saveBuyEntity(deliveryDTO, principal));
    }
    @Transactional
    public void saveDelivery(DeliveryDTO deliveryDTO, Principal principal){
        BuyEntity buyEntity = buyRepository.findByUserid(principal.getName());
        deliveryRepository.save(DeliveryEntity.saveDeliveryEntity(deliveryDTO, principal, buyEntity));

    }
}
