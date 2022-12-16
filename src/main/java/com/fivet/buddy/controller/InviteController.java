package com.fivet.buddy.controller;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.services.InviteService;
import com.fivet.buddy.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/invite/")
public class InviteController {
    @Autowired
    private InviteService inviteService;

    @Autowired
    private HttpSession session;


    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 초대 테스트페이지 이동
    @RequestMapping("test")
    public String test()throws Exception{
        return "testInvite";
    }

    // 초대확인 테스트 페이지 이동
    @RequestMapping("testInviteConfirm")
    public String testInviteConfirm() throws Exception{
        return "testInviteConfirm";
    }

    // 초대코드 확인
    @ResponseBody
    @RequestMapping("codeCheck")
    public boolean codeCheck(String inviteCode) throws Exception{
        boolean result = inviteService.codeCheck(inviteCode);
        return result;
    }

    // 초대된 팀으로 insert


    // 초대코드 insert
    @ResponseBody
    @RequestMapping("codeInsert")
    public void codeInsert(String inviteCode) throws Exception{
        inviteService.codeInsert(inviteCode);
    }

    // 초대코드 delete
    @RequestMapping("codeDelete")
    public String codeDelete(String inviteCode) throws Exception{
        System.out.println("도착");
        System.out.println(inviteCode);
        inviteService.codeDelete(inviteCode);
        return "redirect:/";
    }

}
