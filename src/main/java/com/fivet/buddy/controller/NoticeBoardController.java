package com.fivet.buddy.controller;

import com.fivet.buddy.dto.NoticeBoardDTO;
import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.services.MemberService;
import com.fivet.buddy.services.NoticeBoardService;
import com.fivet.buddy.services.NoticeFileService;

import com.fivet.buddy.util.FileUtil;
import com.fivet.buddy.util.PageNavi;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/notice/")
public class NoticeBoardController {

    @Autowired
    private HttpSession session;
    @Autowired
    private NoticeFileService noticeFileService;

    @Autowired
    private NoticeBoardService noticeBoardService;

    @Value("C:/files/noticeImg")
    String noticePath;

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
    public String toAdminNotice(int cpage, Model model) throws Exception {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            int rcpp = 10; // RecordCountPerPage
            int ncpp = 10; // NaviCountPagePage
            int rtc = noticeBoardService.totalCount(); // RecodeTotalCount
            Map<String, Integer> param = new HashMap<>();
            param.put("start", (cpage-1)*rcpp+1);
            param.put("end", cpage*rcpp);
            List<NoticeBoardDTO> noticeBoardList = noticeBoardService.selectNoticePage(param);
            String pageNavi = new PageNavi().getPageNaviAll(cpage, rcpp, ncpp, rtc, "/notice/toAdminNotice", "cpage");
            model.addAttribute("noticeBoardList", noticeBoardList);
            model.addAttribute("pageNavi", pageNavi);
            return "admin/adminNotice/adminNotice";
        } else {
            return "error";
        }
    }

    //공지사항 글쓰기 페이지로 이동.
    @RequestMapping("toAdminNoticeWrite")
    public String toAdminNoticeWrite() {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            return "admin/adminNotice/adminNoticeWrite";
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
        return "redirect:/notice/toAdminNotice?cpage=1";
    }

    //공지사항 삭제
    @RequestMapping("deleteNotice")
    public String deleteNotice(int noticeSeq) {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            noticeBoardService.deleteNotice(noticeSeq);
            return "redirect:/notice/toAdminNotice?cpage=1";
        } else {
            return "error";
        }

    }

    // 공지글 보기
    @RequestMapping("adminNoticeDetail")
    public String adminNoticeDetail(int noticeSeq, Model model){
        if (session.getAttribute("memberLogtype").equals("admin")) {
            model.addAttribute("notice", noticeBoardService.noticeDetail(noticeSeq));
            return "admin/adminNotice/adminNoticeDetail";
        } else {
            return "error";
        }
    }

    // 공지글 수정화면으로 가기
    @RequestMapping("toNoticeModify")
    public String adminNoticeModify(int noticeSeq, Model model) {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            NoticeBoardDTO noticeBoardDto = noticeBoardService.noticeDetail(noticeSeq);
            model.addAttribute("notice",noticeBoardDto);
            return "admin/adminNotice/adminNoticeModify";
        } else {
            return "error";
        }
    }

    // 공지글 수정
    @PostMapping("modifyNotice")
    public String modifyNotice(NoticeBoardDTO noticeBoardDto) {
        noticeBoardService.updateNotice(noticeBoardDto);
        return "redirect:/notice/toAdminNotice";
    }

    //공지글 이미지 첨부 (ajax)
    @ResponseBody
    @PostMapping("imageUpload")
    public String imageUpload(@RequestParam MultipartFile uploadFile, Model model, NoticeFileDTO noticeFileDto, FileUtil util) throws Exception {
        String sysName = UUID.randomUUID().toString() + uploadFile.getOriginalFilename();
        util.save(uploadFile, noticePath, sysName);
        return "/noticeImg/"+sysName;
    }
}
