package com.fivet.buddy.controller;

import com.fivet.buddy.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/")
public class EmailController {

    private final EmailUtil emailUtil;

    @PostMapping("sendEmail")
    public Map<String, Object> sendEmail(@RequestBody Map<String, Object> params){
        return emailUtil.sendEmail( (String) params.get("userId")
                , (String) params.get("subject")
                , (String) params.get("body")
        );
    }
    @ResponseBody
    @PostMapping("emailProve")
    public String emailProve() throws  Exception{
        String ranVal = createEmailKey();
        System.out.println(ranVal);
        return ranVal;
    }

    public String createEmailKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 이메일 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
