package com.fivet.buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private HttpSession session;
    @RequestMapping("/")
    public String home(Model model) {
        if (session.getAttribute("memberSeq")==null) {
            return "index";
        } else {
            return "redirect:/member/loginIndex";
        }

    }
}
