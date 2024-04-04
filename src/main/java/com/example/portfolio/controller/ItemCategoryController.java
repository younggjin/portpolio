package com.example.portfolio.controller;

import com.example.portfolio.dto.ItemCategoryDTO;
import com.example.portfolio.entity.ItemCategoryEntity;
import com.example.portfolio.repository.ItemCategoryRepository;
import com.example.portfolio.service.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    @GetMapping("/admin/category_write")
    public String category_write(Model model) {//카테고리 첫번째 데이터 뽑음
        List<ItemCategoryDTO> category1 = itemCategoryService.category_first();

        model.addAttribute("first",category1);
        return "/admin/category_write";
    }

    @PostMapping("/admin/category_proc")
    public String category_save(ItemCategoryDTO itemCategoryDTO){//카테고리 내용 저장

        itemCategoryService.category_save(itemCategoryDTO);

        return "redirect:/admin/category_write";
    }

    @ResponseBody
    @PostMapping("/admin/category_first")
    public List<ItemCategoryDTO> category_register(@RequestParam("category1")String category1)
    {
        return itemCategoryService.category_second(category1);
    }

    @PostMapping("/admin/category_second")
    @ResponseBody
    public List<ItemCategoryDTO> findCategoryPidx(@RequestParam("category1") String category1, @RequestParam("category2") String category2){

        return itemCategoryService.category_third(category1, category2);
    }
    @PostMapping("/admin/category2")
    @ResponseBody
    public List<ItemCategoryDTO> findSecond(@RequestParam("category1")String category1){

        return itemCategoryService.categorySecondList(category1);
    }
    @PostMapping("/admin/category3")
    @ResponseBody
    public List<ItemCategoryDTO> findThird(@RequestParam("category1")String category1, @RequestParam("category2")String category2){

        return itemCategoryService.categoryThirdList(category1, category2);
    }


}
