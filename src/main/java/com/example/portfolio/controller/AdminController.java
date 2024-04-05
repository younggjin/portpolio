package com.example.portfolio.controller;

import com.example.portfolio.dto.ItemCategoryDTO;
import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.dto.MemberDTO;
import com.example.portfolio.repository.ItemFileRepository;
import com.example.portfolio.repository.ItemRepository;
import com.example.portfolio.service.ItemCategoryService;
import com.example.portfolio.service.ItemService;
import com.example.portfolio.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final ItemFileRepository itemFileRepository;
    private final ItemRepository itemRepository;

    private final ItemCategoryService itemCategoryService;

    public AdminController(MemberService memberService, ItemService itemService, ItemFileRepository itemFileRepository, ItemRepository itemRepository, ItemCategoryService itemCategoryService) {
        this.memberService = memberService;
        this.itemService = itemService;
        this.itemFileRepository = itemFileRepository;
        this.itemRepository = itemRepository;
        this.itemCategoryService = itemCategoryService;
    }

    @GetMapping("/admin/mem_list")
    public String mem_list( Model model, @PageableDefault(page=1) Pageable pageable){

        Page<MemberDTO> memberList = memberService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < memberList.getTotalPages()) ? startPage + blockLimit - 1 : memberList.getTotalPages();

        model.addAttribute("list", memberList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/admin/mem_list";
    }
    @GetMapping("/admin/mem_view/{idx}")
    public String mem_view(Model model, @PathVariable(name="idx") Long idx, HttpServletResponse response, HttpServletRequest request){

        MemberDTO memberDTO = memberService.findById(idx, response, request);


        model.addAttribute("view", memberDTO);

        return "/admin/mem_view";
    }
    @GetMapping("/admin/mem_delete/{idx}")
    public String mem_delete(@PathVariable(name="idx") Long idx){

        memberService.memberdelete(idx);

        return "redirect:/admin/mem_list";
    }


    @GetMapping("/admin/item_write")
    public String item_write(Model model){

        model.addAttribute("category1", itemCategoryService.categoryFirstList());

        return "/admin/item_write";
    }

    @PostMapping("/admin/item_write_proc")
    public String item_write_proc(ItemDTO itemDTO) throws IOException {

        itemService.saveItem(itemDTO);

        return "redirect:/admin/item_list";
    }

    @GetMapping("/admin/item_list")
    public String item_list(Model model, @PageableDefault(page=1) Pageable pageable){
        Page<ItemDTO> itemList = itemService.paging(pageable);

        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < itemList.getTotalPages()) ? startPage + blockLimit - 1 : itemList.getTotalPages();

        model.addAttribute("list", itemList);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "admin/item_list";
    }

    @GetMapping("/admin/item_view/{idx}")
    public String item_view(Model model, @PathVariable(name="idx") Long idx){

        ItemDTO itemDTO = itemService.findById(idx);
        model.addAttribute("list", itemDTO);

        String first = String.valueOf(itemDTO.getCategory1());
        String second = String.valueOf(itemDTO.getCategory2());

        List<ItemCategoryDTO> category1 = itemCategoryService.categoryFirstList();
        model.addAttribute("category1", category1);

        List<ItemCategoryDTO> category2 = itemCategoryService.categorySecondList(first);
        model.addAttribute("category2", category2);

        List<ItemCategoryDTO> category3 = itemCategoryService.categoryThirdList(first, second);
        model.addAttribute("category3", category3);

        return "/admin/item_view";
    }

    @PostMapping("/admin/item_modify_proc/{idx}")
    public String item_modify(@ModelAttribute ItemDTO itemDTO) throws IOException {

        itemService.itemmodify(itemDTO);

        return "redirect:/admin/item_list";
    }

    @GetMapping("/admin/item_delete/{idx}")
    public String item_delete(@PathVariable(name="idx") Long idx){

        itemService.itemdelete(idx);

        return "redirect:/admin/item_list";
    }
}
