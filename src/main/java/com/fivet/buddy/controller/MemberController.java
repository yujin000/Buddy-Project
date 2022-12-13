package com.fivet.buddy.controller;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 회원가입 페이지 이동
    @RequestMapping("toSignUp")
    public String toSignUp() throws Exception{
        return "signup";
    }

    // 회원가입 (signUp)
    @RequestMapping("signUp")
    public String signUp(MemberDTO memberDto) throws Exception{
        memberService.signUp(memberDto);
        return "redirect:/";
    }


}
