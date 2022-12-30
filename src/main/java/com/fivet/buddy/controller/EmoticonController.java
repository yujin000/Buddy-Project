package com.fivet.buddy.controller;

import com.fivet.buddy.dto.EmoticonDTO;
import com.fivet.buddy.services.EmoticonService;
import com.fivet.buddy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/emoticon/")
public class EmoticonController {

    @Autowired
    private EmoticonService emoticonService;

    @RequestMapping("emoticonMain")
    public String emoticonMain() {
            return "admin/adminEmoticon";
    }

    @Value("C:/files/emoticon/")
    String emoticonPath;
    @RequestMapping("insertEmoticon")
    public String insertEmoticon(EmoticonDTO emoticonDto, MultipartFile file, FileUtil util) throws Exception {
        String emoticonOriName = file.getOriginalFilename();
        String emoticonSysName = UUID.randomUUID() + "_" + emoticonOriName;
        //UUID.randomUUID() : 현재 시간과 자체 매커니즘을 통해 겹치지 않는 기다란 문자를 자동으로 생성해줌
        util.save(file,emoticonPath,emoticonSysName);
        emoticonDto.setEmoticonOriName(emoticonOriName);
        emoticonDto.setEmoticonSysName(emoticonSysName);
        emoticonService.insertEmoticon(emoticonDto);
        return "redirect:/emoticon/emoticonMain";
    }
}
