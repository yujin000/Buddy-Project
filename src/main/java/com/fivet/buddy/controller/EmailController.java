package com.fivet.buddy.controller;

import com.fivet.buddy.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/")
public class EmailController {

    private final EmailUtil emailUtil;

    @PostMapping("sendEmail")
    public Map<String, Object> sendEmail(@RequestBody Map<String, Object> params){
        log.info("email params={}", params);

        return emailUtil.sendEmail( (String) params.get("userId")
                , (String) params.get("subject")
                , (String) params.get("body")
        );
    }
}
