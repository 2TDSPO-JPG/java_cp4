package com.blackmarket.blackmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/firstpage")
    public String firstpage() {
        return "firstpage";
    }

}