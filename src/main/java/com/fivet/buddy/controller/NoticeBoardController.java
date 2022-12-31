package com.fivet.buddy.controller;

import com.fivet.buddy.dao.NoticeBoardDAO;
import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.NoticeBoardDTO;
import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.services.MemberService;
import com.fivet.buddy.services.NoticeBoardService;
import com.fivet.buddy.services.NoticeFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
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

    @Autowired
    private MemberService memberService;

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
        noticeBoardDto.setNoticeWriterSeq((int)session.getAttribute("memberSeq"));
        noticeBoardDto.setNoticeWriterName(session.getAttribute("memberName").toString());
        noticeBoardDto.setNoticeContents(noticeBoardDto.getNoticeContents().replace("<script>","&lt;script>"));
        noticeBoardService.insertNotice(noticeBoardDto);
        return "redirect:/notice/toAdminNotice";
    }

    //공지사항 삭제
    @PostMapping("deleteNotice")
    public String deleteNotice(int noticeSeq) {
        noticeBoardService.deleteNotice(noticeSeq);
        return "redirect:/notice/toAdminNotice";
    }

    // 공지글 보기
    @RequestMapping("adminNoticeDetail")
    public String adminNoticeDetail(int noticeSeq, Model model){
        if (session.getAttribute("memberLogtype").equals("admin")) {
            model.addAttribute("notice", noticeBoardService.noticeDetail(noticeSeq));
            return "/admin/adminNoticeDetail";
        } else {
            return "error";
        }

    }
}
