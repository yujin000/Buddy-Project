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
        // 세션이 있으면 mypage.html, 세션 없으면 index로 이동(이중 로그인 방지)
        if(session.getAttribute("memberSeq") == null){
            return "index";
        }else{
            model.addAttribute("memberSeq",session.getAttribute("memberSeq"));
            return "indexError";
        }

    }
}
