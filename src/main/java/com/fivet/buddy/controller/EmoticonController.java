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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/emoticon/")
public class EmoticonController {

    @Autowired
    private EmoticonService emoticonService;

    @RequestMapping("emoticonMain")
    public String emoticonMain(Model model) throws Exception {
        List<EmoticonDTO> emoticonDto = emoticonService.selectEmoticon();
        model.addAttribute("emoticonDto",emoticonDto);
        return "admin/adminEmoticon";
    }

    @Value("C:/files/emoticon/")
    String emoticonPath;
    @RequestMapping("insertEmoticon")
    public String insertEmoticon(EmoticonDTO emoticonDto, MultipartFile file, FileUtil util) throws Exception {
        String emoticonOriName = file.getOriginalFilename();
        String emoticonSysName = UUID.randomUUID() + "_" + emoticonOriName;
        //UUID.randomUUID() : 현재 시간과 자체 매커니즘을 통해 겹치지 않는 기다란 문자를 자동으로 생성해줌
        emoticonDto.setEmoticonOriName(emoticonOriName);
        emoticonDto.setEmoticonSysName(emoticonSysName);
        util.save(file,emoticonPath,emoticonSysName);
        emoticonService.insertEmoticon(emoticonDto);
        System.out.println("save : " +  emoticonSysName);
        System.out.println("getSysname : " +emoticonDto.getEmoticonSysName());
        return "redirect:/emoticon/emoticonMain";
    }

}
