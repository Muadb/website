package com.website.bce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String greetings() {
        return "bce";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
