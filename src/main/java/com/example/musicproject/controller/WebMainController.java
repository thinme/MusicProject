package com.example.musicproject.controller;

import com.example.musicproject.model.WebMain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebMainController {
    private final static String  WebMain ="WebMain";

    @RequestMapping("/WebMain")
    public String getWebMian(Model model){
        WebMain main = new WebMain();
        model.addAttribute("main", main);



        return WebMain;
    }
}
