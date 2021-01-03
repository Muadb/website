package com.website.bce.controller;

import com.website.bce.dtos.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping(path = "/email/me", consumes = "application/x-www-form-urlencoded")
    public String email(Contact contact) {
        return "thanks";
    }
}