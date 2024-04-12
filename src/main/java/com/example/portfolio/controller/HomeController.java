package com.example.portfolio.controller;

import com.example.portfolio.repository.ItemCategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class HomeController {

    private final ItemCategoryRepository itemCategorizeRepository;

    @Value("${save.path}")
    private String path;

    public HomeController(ItemCategoryRepository itemCategorizeRepository) {
        this.itemCategorizeRepository = itemCategorizeRepository;
    }

    @GetMapping("/")
    public String home(Model model){

        return "/home";
    }
    @GetMapping("/index")
    public String index(){
        return "/index";
    }


}
