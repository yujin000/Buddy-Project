package com.fivet.buddy.controller;

import com.fivet.buddy.dto.SmsDTO;
import com.fivet.buddy.dto.SmsResponseDTO;
import com.fivet.buddy.services.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@RequiredArgsConstructor
@Controller
public class SmsController {

    private final SmsService smsService;

    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    @GetMapping("/send")
    public String getSmsPage() {
        return "sendSms";
    }

    @ResponseBody
    @PostMapping("/sms/send")
    public String sendSms(@RequestBody SmsDTO smsDto, Model model) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        // 랜덤 번호 변수
        String confirmVal = createSmsKey();
        // set 랜덤 번호
        smsDto.setContent("[Buddy] 회원가입 인증번호 [" + confirmVal + "]를 화면에 입력해주세요.");
        // 문자 발송
        SmsResponseDTO response = smsService.sendSms(smsDto);
        // 응답확인(요청보낸 아이디, 요청 시간, 상태)
        model.addAttribute("response", response);
        // html에 확인 번호 발송(비교하기 위해)
        model.addAttribute("confirmVal", confirmVal);
        return confirmVal;
    }

    // 랜덤 번호 5자리 생성
    public String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        System.out.println(key);
        return key.toString();
    }


}
