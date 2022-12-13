package com.fivet.buddy.controller;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

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

    // 로그인
    @RequestMapping("login")
    public String isAccountExist(MemberDTO memberDto, Model model) throws Exception{
        boolean result = memberService.isAccountExist(memberDto);
        if(result){
            MemberDTO dto = memberService.selectAccountInfo(memberDto);
            model.addAttribute("memberInfo",dto);
            return "mypage";
        }else{
            logger.info("2");
            return "redirect:/";
        }

    }

    // 카카오 로그인
    @RequestMapping("kakaoLogin")
    public String kakaoLogin()throws Exception{
        return "redirect:/";
    }

}
