package com.example.portfolio.service;

import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.entity.MemberEntity;
import com.example.portfolio.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void memberwrite(MemberDTO memberDTO){

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        memberEntity.setPwd(bCryptPasswordEncoder.encode(memberDTO.getPassword()));

        memberRepository.save(memberEntity);
    }

    public String useridCheck(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        if(byUserid.isPresent()){
            //조회결과 있다 -> 아이디 사용 불가능
            return null;
        }else{
            //조회결과 없다 -> 아이디 사용 가능
            return "ok";
        }
    }

    public Page<MemberDTO> paging(Pageable pageable){
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 2; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<MemberEntity> memberEntities = memberRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "idx")));

        // 목록: id, userid, name, regdate
        Page<MemberDTO> memberDTOS = memberEntities.map(member -> new MemberDTO(member.getIdx(), member.getUserid(), member.getName(), member.getRegdate()));
        return memberDTOS;
    }

    public MemberDTO findById(Long idx, HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        List<String> cookies_name = new ArrayList<>();
        for(int a=0; a<cookies.length; a++){
            cookies_name.add(cookies[a].getValue());
        }
        if(!cookies_name.contains("cookie"+idx))
        {
            Cookie cookie = new Cookie("cookie"+String.valueOf(idx), "cookie"+String.valueOf(idx));
            cookie.setPath("/admin/mem_view");
            cookie.setMaxAge(60*60);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);

            /*
                noticeEntity entity = noticeRepository.findById().orElse(null);
                int count = entity.getHit();
                count = count +1;
                entity.setHit(count);
                noticeRepository.save(entity);
            */
        }
        Optional<MemberEntity> memberEntity = memberRepository.findById(idx);

        if(memberEntity.isPresent()) {

            MemberEntity member = memberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(member);

            return memberDTO;
        }else{
            return null;
        }
    }
    public MemberEntity memberdelete(Long idx){

        Optional<MemberEntity> memberEntity = memberRepository.findById(idx);
        memberRepository.delete(memberEntity.get());

        return null;
    }

}
