package com.fivet.buddy.controller;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.services.InviteService;
import com.fivet.buddy.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
