package com.example.portfolio.controller;

import com.example.portfolio.dto.ItemCategoryDTO;
import com.example.portfolio.service.ItemCategoryService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class NavController {

    private final ItemCategoryService itemCategoryService;

    public NavController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @ModelAttribute("first_category")
    public List<ItemCategoryDTO> first_category(){

        return itemCategoryService.findAll();
    }
    @ModelAttribute("second_category")
    public List<ItemCategoryDTO> second_category(){

        return itemCategoryService.findAll();
    }
    @ModelAttribute("third_category")
    public List<ItemCategoryDTO> third_category(){

        return itemCategoryService.findAll();
    }
}
