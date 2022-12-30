package com.fivet.buddy.controller;

import com.fivet.buddy.dao.NoticeBoardDAO;
import com.fivet.buddy.dto.NoticeBoardDTO;
import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.services.NoticeBoardService;
import com.fivet.buddy.services.NoticeFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice/")
public class NoticeBoardController {

    @Autowired
    private HttpSession session;
    @Autowired
    private NoticeFileService noticeFileService;

    @Autowired
    private NoticeBoardService noticeBoardService;

    //공지글 보기(회원)
    @ResponseBody
    @RequestMapping("detail")
    public List<Map<String,String>> selectDetail(int noticeSeq) throws Exception{
        List<NoticeFileDTO> noticeFileDto = noticeFileService.selectFile(noticeSeq);
        List<Map<String,String>> list = new ArrayList<>();
        for(int i = 0; i<noticeFileDto.size();i++){
            Map<String, String> map = new HashMap<>();
            map.put("noticeOriName" , noticeFileDto.get(i).getNoticeOriname());
            map.put("noticeSysName", noticeFileDto.get(i).getNoticeSysname());
            list.add(map);
        }
        return list;
    }

    //공지 관리 페이지로 이동.
    @RequestMapping("toAdminNotice")
    public String toAdminNotice(Model model) throws Exception {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            List<NoticeBoardDTO> noticeBoardList = noticeBoardService.selectNotice();
            model.addAttribute("noticeBoardList", noticeBoardList);
            return "/admin/adminNotice";
        } else {
            return "error";
        }
    }

    //공지사항 글쓰기 페이지로 이동.
    @RequestMapping("toAdminNoticeWrite")
    public String toAdminNoticeWrite() {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            return "/admin/adminNoticeWrite";
        } else {
            return "error";
        }
    }

    //공지사항 글쓰기
    @RequestMapping("insertNotice")
    public String insertNotice(NoticeBoardDTO noticeBoardDto) {
        noticeBoardDto.setNoticeWriter((int)session.getAttribute("memberSeq"));
        noticeBoardService.insertNotice(noticeBoardDto);
        return "redirect:/notice/toAdminNotice";
    }
}
