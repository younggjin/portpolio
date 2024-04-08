package com.example.portfolio.service;

import com.example.portfolio.dto.CartDTO;
import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.entity.CartEntity;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.CartRepository;
import com.example.portfolio.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class DeliveryService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public DeliveryService(MemberRepository memberRepository, CartRepository cartRepository) {
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
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
}
