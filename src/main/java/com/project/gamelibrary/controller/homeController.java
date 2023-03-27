package com.project.gamelibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/manager")
    public String manger() {
        System.out.println("manager 진입");
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("admin 진입");
        return "redirect:/";
    }
}
