package com.example.portfolio.controller;

import com.example.portfolio.dto.DeliveryDTO;
import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.dto.ItemListDTO;
import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.service.DeliveryService;
import com.example.portfolio.service.ItemService;
import com.example.portfolio.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final DeliveryService deliveryService;

    public MemberController(MemberService memberService, ItemService itemService, DeliveryService deliveryService) {
        this.memberService = memberService;
        this.itemService = itemService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/member/login")
    public String login(){

        return "/member/login";
    }

    @GetMapping("/member/mem_write")
    public String mem_write(){

        return "/member/mem_write";
    }

    @PostMapping("/member/mem_write_proc")
    public String mem_write_proc(MemberDTO memberDTO){

        memberService.memberwrite(memberDTO);

        return "redirect:/member/login";
    }

    @PostMapping("/member/userid-check")
    public @ResponseBody String useridCheck(@RequestParam(name="userid") String userid){
        String checkResult = memberService.useridCheck(userid);

        if(checkResult != null){
            return "ok";
        }else{
            return "no";
        }
    }

    @GetMapping("/member/item_list")
    public String item_list(Model model, @PageableDefault(page=1) Pageable pageable){
        Page<ItemDTO> itemList = itemService.paging(pageable);



        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();

        model.addAttribute("list", itemList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/member/item_list";
    }
    @GetMapping("/member/item_list/{idx}")
    public String item_list_category(Model model, @PageableDefault(page=1) Pageable pageable, @PathVariable(name="idx")Long idx){
        int blockLimit = 5;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = 0;

        if(idx == 0){
            Page<ItemDTO> itemList = itemService.paging(pageable);
            endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();
            model.addAttribute("list", itemList);

        }else {
            Page<ItemListDTO> itemList = itemService.categoryPaging(pageable, idx);
            endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();
            model.addAttribute("list", itemList);
        }

        model.addAttribute("category_idx", idx);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/member/item_list";
    }

    @GetMapping("/member/item_view/{idx}")
    public String item_view(Model model, @PathVariable(name="idx") Long idx){

        ItemDTO itemDTO = itemService.findById(idx);
        model.addAttribute("list", itemDTO);

        return "/member/item_view";
    }

    @GetMapping("/member/mypage")
    public String mypage(Model model, Principal principal){
        //회원 정보
        MemberDTO member = deliveryService.findMember(principal);

        model.addAttribute("memberList", member);
        //구매상품목록 정보
        DeliveryDTO delivery = deliveryService.findDelivery(principal);

        model.addAttribute("deliveryList", delivery);
        
        //배송지 정보


        
        return "/item/mypage";
    }
}
