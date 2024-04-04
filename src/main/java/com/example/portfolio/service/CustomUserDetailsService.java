package com.example.portfolio.service;

import com.example.portfolio.dto.CustomUserDetails;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        Optional<MemberEntity> memberEntity = memberRepository.findByUserid(userid);

        if(memberEntity != null){
            //System.out.println(memberEntity.get().getUserid());
            return new CustomUserDetails(memberEntity);
        }

        return null;
    }
}
