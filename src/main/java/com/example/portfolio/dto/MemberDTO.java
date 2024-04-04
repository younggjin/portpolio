package com.example.portfolio.dto;

import com.example.portfolio.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long idx;
    private String userid;
    private String password;
    private String name;

    private String add1;
    private String add2;
    private String add3;

    private String mail;
    private String web;

    private String tel1;
    private String tel2;
    private String tel3;

    private int cash;

    private String role;

    private LocalDateTime regdate;

    public MemberDTO(Long idx, String userid, String name, LocalDateTime regdate) {
        this.idx = idx;
        this.userid = userid;
        this.name = name;
        this.regdate = regdate;
    }

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setIdx(memberEntity.getIdx());
        memberDTO.setUserid(memberEntity.getUserid());
        memberDTO.setPassword(memberEntity.getPwd());
        memberDTO.setName(memberEntity.getName());

        memberDTO.setAdd1(memberEntity.getAddress().split("@")[0]);
        memberDTO.setAdd2(memberEntity.getAddress().split("@")[1]);
        memberDTO.setAdd3(memberEntity.getAddress().split("@")[2]);

        memberDTO.setMail(memberEntity.getEmail().split("@")[0]);
        memberDTO.setWeb(memberEntity.getEmail().split("@")[1]);

        memberDTO.setTel1(memberEntity.getTel().split("-")[0]);
        memberDTO.setTel2(memberEntity.getTel().split("-")[1]);
        memberDTO.setTel3(memberEntity.getTel().split("-")[2]);
        memberDTO.setCash(memberEntity.getCash());
        memberDTO.setRole(memberEntity.getRole());
        memberDTO.setRegdate(memberEntity.getRegdate());

        return memberDTO;
    }


}
