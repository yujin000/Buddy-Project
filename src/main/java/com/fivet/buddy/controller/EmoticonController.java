package com.fivet.buddy.controller;

import com.fivet.buddy.dto.EmoticonDTO;
import com.fivet.buddy.services.EmoticonService;
import com.fivet.buddy.util.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/emoticon/")
public class EmoticonController {

    @Autowired
    private EmoticonService emoticonService;

    //이모티콘 관리자 페이지로
    @RequestMapping("emoticonMain")
    public String emoticonMain(Model model) throws Exception {
        //이모티콘 출력
        List<EmoticonDTO> emoticonList = emoticonService.selectEmoticon();
        model.addAttribute("emoticonList",emoticonList);
        return "admin/adminEmoticon";
    }

    //이모티콘 출력
    @ResponseBody
    @RequestMapping("selectEmoticon")
    public String selectEmoticon(Model model) throws Exception {
        List<EmoticonDTO> emoticonList = emoticonService.selectEmoticon();
        Gson g = new Gson();
        return g.toJson(emoticonList);
    }

    //이모티콘 추가

    @Value("C:/files/emoticon/")
    String emoticonPath;
    @Transactional
    @RequestMapping("insertEmoticon")
    public String insertEmoticon(EmoticonDTO emoticonDto, MultipartFile file, FileUtil util) throws Exception {
        String emoticonOriName = file.getOriginalFilename();
        String emoticonSysName = UUID.randomUUID() + "_" + emoticonOriName;
        //UUID.randomUUID() : 현재 시간과 자체 매커니즘을 통해 겹치지 않는 기다란 문자를 자동으로 생성해줌
        emoticonSysName=emoticonSysName.replaceAll("\\s", "");
        emoticonDto.setEmoticonOriName(emoticonOriName);
        emoticonDto.setEmoticonSysName(emoticonSysName);
        util.save(file,emoticonPath,emoticonSysName);
        emoticonService.insertEmoticon(emoticonDto);
//        System.out.println("save : " +  emoticonSysName);
//        System.out.println("getSysname : " +emoticonDto.getEmoticonSysName());
        return "redirect:/emoticon/emoticonMain";
    }

    //이모티콘 삭제
    @RequestMapping("deleteEmoticon")
    public String deleteEmoticon(int emoticonSeq, String emoticonSysName, FileUtil util){
        util.delete(emoticonPath,emoticonSysName);
        emoticonService.deleteEmoticon(emoticonSeq);
        return "redirect:/emoticon/emoticonMain";
    }
}
