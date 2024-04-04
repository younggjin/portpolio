package com.example.portfolio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NoticeController {

    @GetMapping("/notice/notice")
    public String notice(){

        return "/notice/notice";
    }
    @GetMapping("/notice/notice_write")
    public String notice_write(){

        return "/notice/notice_write";
    }
    @GetMapping("/notice/review")
    public String review(){

        return "/notice/review";
    }
    @GetMapping("/notice/review_write")
    public String review_write(){

        return "/notice/review_write";
    }
}
