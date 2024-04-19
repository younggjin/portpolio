package com.example.portfolio.controller;

import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.service.HomeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Controller
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @Value("${save.path}")
    private String path;

    @GetMapping("/")
    public String home(Model model){

        List<ItemDTO> itemDTOList = homeService.newListItem();

        model.addAttribute("itemList", itemDTOList);

        return "/home";
    }

}
