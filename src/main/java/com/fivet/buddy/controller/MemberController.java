package com.fivet.buddy.controller;

import com.fivet.buddy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService mservice;

    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    @RequestMapping("test")
    public String test() throws Exception{
        int result = mservice.test();
        System.out.println(result);
        return "redirect:/";
    }
}
