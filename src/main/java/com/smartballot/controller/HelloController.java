package com.smartballot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String homePage() {
        return "index"; // loads index.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // we will create login.html next
    }
}
