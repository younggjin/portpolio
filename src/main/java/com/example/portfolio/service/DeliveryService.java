package com.example.portfolio.service;

import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.CartRepository;
import com.example.portfolio.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        }
return null;
    }
}
