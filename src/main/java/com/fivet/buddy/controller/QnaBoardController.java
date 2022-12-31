package com.fivet.buddy.controller;

import com.fivet.buddy.dto.*;
import com.fivet.buddy.services.*;
import com.fivet.buddy.util.FileUtil;
import com.fivet.buddy.util.PageNavi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/qna/")
public class QnaBoardController {

    @Autowired
    private QnaBoardService qnaBoardService;

    @Autowired
    private QnaFileService qnaFileService;

    @Autowired
    private QnaCommentService qnaCommentService;

    @Autowired
    private HttpSession session;

    @Autowired
    private NoticeBoardService noticeBoardService;

    @Autowired
    private MemberService memberService;

    @Value("${qna.save.path}")
    String qnaPath;

    //Qna 글쓰기 화면 이동
    @RequestMapping("write")
    public String write() {
        return "customer/customerPopup";
    }

    //고객센터 메인으로
    @RequestMapping("main")
    public String select(Model model) throws Exception {
        int qnaWriter = (int) session.getAttribute("memberSeq");
        List<NoticeBoardDTO> noticeDto = noticeBoardService.selectNotice();
        List<QnaBoardDTO> qnaDto = qnaBoardService.select(qnaWriter);
        model.addAttribute("qna", qnaDto);
        model.addAttribute("notice", noticeDto);
        return "customer/customer";
    }

    //Qna글쓰기
    @RequestMapping("insert")
    public String insert(@RequestParam MultipartFile[] uploadfile, Model model, QnaBoardDTO qnaDto, QnaFileDTO qnaFileDto, FileUtil util) throws Exception {
        qnaDto.setQnaWriterSeq((int) session.getAttribute("memberSeq"));
        qnaDto.setQnaWriterId(memberService.getMemberId(qnaDto.getQnaWriterSeq()));
        System.out.println(uploadfile[0]);
        if (uploadfile[0].isEmpty()) {
            qnaBoardService.insert(qnaDto);
        } else {
            qnaBoardService.insert(qnaDto);
            List<QnaFileDTO> list = new ArrayList<>();
            for (MultipartFile file : uploadfile) {
                if (!file.isEmpty()) {
                    String sysName = UUID.randomUUID().toString() + file.getOriginalFilename();
                    qnaFileDto = new QnaFileDTO(
                            0,
                            file.getOriginalFilename(),
                            sysName,
                            qnaDto.getQnaSeq());
                    list.add(qnaFileDto);
                    util.saves(uploadfile, qnaPath, sysName);
                }
            }
            model.addAttribute("files", list);
            qnaFileService.insertFile(qnaFileDto);
        }
        return "redirect:main";
    }

    //Qna본문글 보기(ajax)
    @ResponseBody
    @PostMapping(value = "detail")
    public List<Map<String, String>> selectDetail(int qnaSeq) throws Exception {
        List<QnaFileDTO> qnaFileDto = qnaFileService.selectFile(qnaSeq);
        List<QnaCommentDTO> qnaCommentDto = qnaCommentService.selectComment(qnaSeq);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < qnaCommentDto.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("qnaCommentContents", qnaCommentDto.get(i).getQnaCommentContents());
            list.add(map);
        }
        for (int i = 0; i < qnaFileDto.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("qnaOriName", qnaFileDto.get(i).getQnaOriName());
            map.put("qnaSysName", qnaFileDto.get(i).getQnaSysName());
            list.add(map);
        }
        return list;
    }

    //Qna삭제
    @RequestMapping("delete")
    public String delete(int qnaSeq, FileUtil util, String qnaSysName) throws Exception {
        if (qnaSysName == null) {
            qnaBoardService.delete(qnaSeq);
        } else {
            qnaBoardService.delete(qnaSeq);
            qnaFileService.deleteFile(qnaSeq);
            util.delete(qnaPath, qnaSysName);
        }
        return "redirect:main";
    }

    //Qna댓글 삭제
    @RequestMapping("deleteComment")
    public String delete(int qnaSeq, int qnaCommentSeq) throws Exception {
        qnaCommentService.deleteComment(qnaSeq, qnaCommentSeq);
        return "redirect:/";
    }

    //Qna 이미지 다운로드
    @RequestMapping("download")
    public ResponseEntity<Resource> download(FileUtil util, String sysName, String oriName) throws Exception {
        return util.download(qnaPath, sysName, oriName);
    }

    //관리자 페이지 (1:1문의)로 이동.
    @RequestMapping("toAdminQna")
    public String toAdminQna(int cpage, Model model) throws Exception {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            //List<QnaBoardDTO> QnaList = qnaBoardService.selectQnaBoardAll();
            int rcpp = 10; // RecordCountPerPage
            int ncpp = 10; // NaviCountPagePage
            int rtc = qnaBoardService.totalCount();
            Map<String, Integer> param = new HashMap<>();
            param.put("start", (cpage-1)*rcpp+1);
            param.put("end", cpage*rcpp);
            List<QnaBoardDTO> QnaList = qnaBoardService.selectQnaBoardPage(param);
            String pageNavi = new PageNavi().getPageNaviAll(cpage, rcpp, ncpp, rtc, "/qna/toAdminQna", "cpage");
            model.addAttribute("qnaList", QnaList);
            model.addAttribute("pageNavi", pageNavi);
            return "/admin/adminQna";
        } else {
            return "error";
        }
    }

    //관리자 페이지에서 1:1문의 본문 보기.
    @RequestMapping("adminQnaDetail")
    public String adminQnaDetail(QnaBoardDTO qnaBoardDto, Model model) throws Exception {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            qnaBoardDto = qnaBoardService.selectDetail(qnaBoardDto.getQnaSeq());
            List<QnaCommentDTO> qnaComment = qnaCommentService.selectComment(qnaBoardDto.getQnaSeq());
            int qnaCommentCount = qnaCommentService.count(qnaBoardDto.getQnaSeq());
            model.addAttribute("qna", qnaBoardDto);
            model.addAttribute("qnaComment", qnaComment);
            model.addAttribute("qnaCommentCount", qnaCommentCount);
            return "/admin/adminQnaDetail";
        } else {
            return "error";
        }
    }

    // 문의내역 답글 달기
    @PostMapping("insertComment")
    public String insertComment(QnaCommentDTO qnaCommentDto) {
        if (session.getAttribute("memberLogtype").equals("admin")) {
           qnaCommentDto.setQnaCommentWriter((int)session.getAttribute("memberSeq"));
           qnaCommentService.insertComment(qnaCommentDto);
           return "redirect:/qna/adminQnaDetail?qnaSeq=" + qnaCommentDto.getQnaSeq();
        } else {
            return "error";
        }
    }
}
