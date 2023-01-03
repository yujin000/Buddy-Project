package com.fivet.buddy.controller;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.services.InviteService;
import com.fivet.buddy.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/")
public class EmailController {

    @Autowired
    private InviteService inviteService;

    private final EmailUtil emailUtil;

    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 이메일 발송
    @PostMapping("sendEmail")
    public Map<String, Object> sendEmail(@RequestBody Map<String, Object> params){
        return emailUtil.sendEmail( (String) params.get("userId")
                , (String) params.get("subject")
                , (String) params.get("body")
        );
    }

    // 이메일 인증번호 리턴
    @ResponseBody
    @PostMapping("emailProve")
    public String emailProve() throws  Exception{
        String ranVal = createEmailKey();
        return ranVal;
    }

    // 이메일 인증코드 (숫자 6)
    public String createEmailKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 이메일 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        System.out.println(key);
        return key.toString();
    }

    // INVITE
    // 인증 이메일 발송
    @PostMapping("sendInviteEmail")
    public Map<String, Object> sendInviteEmail(@RequestBody Map<String, Object> params){
        return emailUtil.sendInviteEmail( (String) params.get("userId")
                , (String) params.get("subject")
                , (String) params.get("body")
                , (String) params.get("teamName")
        );
    }

    // 초대 인증코드 리턴(초대에선 db에 저장해야함)
    @ResponseBody
    @PostMapping("inviteProve")
    public String inviteProve() throws Exception{
        String ranVal = inviteCode();
        return ranVal;
    }

    // 숫자+영문 10자리 생성
    public String inviteCode() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            if (rnd.nextBoolean()) {
                key.append((char) ((int) (rnd.nextInt(26)) + 97));
            } else {
                key.append((rnd.nextInt(10)));
            }
        }
        System.out.println(key);
        return key.toString();
    }
}
