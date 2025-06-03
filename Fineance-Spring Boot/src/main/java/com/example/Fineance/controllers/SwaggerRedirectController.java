package com.example.Fineance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/docs")
public class SwaggerRedirectController {
    @GetMapping
    public String redirectToSwagger() {
        return "redirect:/swagger-ui/index.html";
    }
}

