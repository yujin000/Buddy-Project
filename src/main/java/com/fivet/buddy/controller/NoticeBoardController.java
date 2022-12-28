package com.fivet.buddy.controller;

import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.services.NoticeFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice/")
public class NoticeBoardController {

    @Autowired
    private NoticeFileService noticeFileService;

    @ResponseBody
    @RequestMapping("detail")
    public List<Map<String,String>> selectDetail(int noticeSeq) throws Exception{
        List<NoticeFileDTO> noticeFileDto = noticeFileService.selectFile(noticeSeq);
        List<Map<String,String>> list = new ArrayList<>();
        for(int i = 0; i<noticeFileDto.size();i++){
            Map<String, String> map = new HashMap<>();
            map.put("noticeOriName" , noticeFileDto.get(i).getNoticeOriname());
            map.put("noticeSysName", noticeFileDto.get(i).getNoticeSysname());
            //System.out.println(map.get("noticeSysName"));
            list.add(map);
        }
        return list;
    }
}
