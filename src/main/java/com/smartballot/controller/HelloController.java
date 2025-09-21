package com.smartballot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "index";  // ✅ Spring will look for templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // ✅ Spring will look for templates/login.html
    }
}
