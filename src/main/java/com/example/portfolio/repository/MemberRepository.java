package com.example.portfolio.repository;

import com.example.portfolio.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //아이디
    Optional<MemberEntity> findByUserid(String userid);
}
