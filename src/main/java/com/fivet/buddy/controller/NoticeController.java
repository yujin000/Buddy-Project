package com.fivet.buddy.controller;

import com.fivet.buddy.dto.NoticeDTO;
import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.services.NoticeFileService;
import com.fivet.buddy.services.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeFileService noticeFileService;

    @RequestMapping("detail")
    public String selectDetail(NoticeDTO noticeDto, NoticeFileDTO noticeFileDto, Model model) throws Exception{
        NoticeDTO noticeDto1 = noticeService.selectDetail(noticeDto);
        NoticeFileDTO noticeFileDto1 = noticeFileService.selectFileDetail(noticeFileDto);
        model.addAttribute("notice", noticeDto1);
        model.addAttribute("noticeFile",noticeFileDto1);
        return "detail";
    }
}
