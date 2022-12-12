package com.fivet.buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class home_controller {
    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
