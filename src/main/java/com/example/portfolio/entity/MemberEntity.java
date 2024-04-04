package com.example.portfolio.entity;

import com.example.portfolio.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member_table")
public class MemberEntity extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userid;

    @Column
    private String pwd;

    @Column
    private String name;

    @Column(length = 500)
    private String address;

    @Column
    private String email;

    @Column
    private String tel;

    @Column
    private int cash;

    @Column
    private String role;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){

        String address = memberDTO.getAdd1()+"@"+memberDTO.getAdd2()+"@"+memberDTO.getAdd3();
        String email = memberDTO.getMail()+"@"+memberDTO.getWeb();
        String tel = memberDTO.getTel1()+"-"+memberDTO.getTel2()+"-"+memberDTO.getTel3();

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserid(memberDTO.getUserid());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setAddress(address);
        memberEntity.setEmail(email);
        memberEntity.setTel(tel);
        memberEntity.setCash(1000000);
        memberEntity.setRole("MEMBER");

        return memberEntity;
    }


}
