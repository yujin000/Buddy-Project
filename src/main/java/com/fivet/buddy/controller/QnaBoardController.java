package com.fivet.buddy.controller;

import com.fivet.buddy.dto.*;
import com.fivet.buddy.services.*;
import com.fivet.buddy.util.FileUtil;
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
        List<QnaDTO> qnaDto = qnaBoardService.select(qnaWriter);
        model.addAttribute("qna", qnaDto);
        model.addAttribute("notice", noticeDto);
        return "customer/customer";
    }

    //Qna글쓰기
    @RequestMapping("insert")
    public String insert(@RequestParam MultipartFile[] uploadfile, Model model, QnaDTO qnaDto, QnaFileDTO qnaFileDto, FileUtil util) throws Exception {
        qnaDto.setQnaWriter((int) session.getAttribute("memberSeq"));
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
        System.out.println(qnaSeq);
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

    @RequestMapping("download")
    public ResponseEntity<Resource> download(FileUtil util, String sysName, String oriName) throws Exception {
        return util.download(qnaPath, sysName, oriName);
    }

    //관리자 페이지 (1:1문의)로 이동.
    @RequestMapping("toAdminQna")
    public String toAdminQna(Model model) {
        if (session.getAttribute("memberLogtype").equals("admin")) {
            List<QnaDTO> QnaList = qnaBoardService.selectQnaBoardAll();
            model.addAttribute("qnaList", QnaList);
            return "/admin/adminQna";
        } else {
            return "error";
        }
    }


}
