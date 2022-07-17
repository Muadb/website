package com.website.bce.controller;

import com.website.bce.dto.PrincipalDto;
import com.website.bce.service.PrincipalService;
import com.website.bce.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    PrincipalService principalService;

    @Autowired
    VisitorService visitorService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        LOGGER.info("visitor");
        visitorService.visitorClientInfo(request);
        return "index";
    }

    @PostMapping(path = "/email/me", consumes = "application/x-www-form-urlencoded")
    public String email(PrincipalDto principalDto, HttpServletRequest request) {
        principalService.principleContact(principalDto, request);
        return "thanks";
    }
}