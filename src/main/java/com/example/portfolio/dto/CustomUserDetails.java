package com.example.portfolio.dto;

import com.example.portfolio.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CustomUserDetails implements UserDetails {

    private Optional<MemberEntity> memberEntity;

    public CustomUserDetails(Optional<MemberEntity> memberEntity){
        this.memberEntity = memberEntity;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+memberEntity.get().getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return memberEntity.get().getPwd();
    }

    @Override
    public String getUsername() {
        return memberEntity.get().getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
