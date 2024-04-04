package com.example.portfolio.service;

import com.example.portfolio.dto.MemberDTO;
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

    public List<MemberDTO> findMember(Principal principal){



        return null;
    }
}
