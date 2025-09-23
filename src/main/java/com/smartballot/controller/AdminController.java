package com.smartballot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"/dashboard", ""})
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/start-election")
    public String startElection() {
        return "admin/start-election";
    }

    @GetMapping("/publish-result")
    public String publishResult() {
        return "admin/publish-result";
    }

    @GetMapping("/cease-election")
    public String ceaseElection() {
        return "admin/cease-election";
    }
}
