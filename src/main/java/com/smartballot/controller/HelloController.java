package com.smartballot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "index.html"; // loads index.html from static folder
    }

    @GetMapping("/login")
    public String login() {
        return "login.html"; // loads login.html from static folder
    }
}
