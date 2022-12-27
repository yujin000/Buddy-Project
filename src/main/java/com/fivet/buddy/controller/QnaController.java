package com.fivet.buddy.controller;

import com.fivet.buddy.dto.NoticeDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import com.fivet.buddy.dto.QnaDTO;
import com.fivet.buddy.dto.QnaFileDTO;
import com.fivet.buddy.services.NoticeService;
import com.fivet.buddy.services.QnaCommentService;
import com.fivet.buddy.services.QnaFileService;
import com.fivet.buddy.services.QnaService;
import com.fivet.buddy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/qna/")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @Autowired
    private QnaFileService qnaFileService;

    @Autowired
    private QnaCommentService qnaCommentService;

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("write")
    public String write() {
        return "customer/customerPopup";
    }


    @Autowired
    private HttpSession session;

    @Value("${qna.save.path}")
    String qnaPath;

    @RequestMapping("main")
    public String select(Model model) throws Exception {
        List<NoticeDTO> noticeDto = noticeService.select();
        List<QnaDTO> qnaDto = qnaService.select();
        System.out.println(qnaDto.get(0).getQnaTitle());
        System.out.println(qnaDto.get(1).getQnaTitle());
        System.out.println(qnaDto.get(2).getQnaTitle());
        model.addAttribute("qna", qnaDto);
        model.addAttribute("notice", noticeDto);
        return "customer/customer";
    }
    @Transactional
    @RequestMapping("insert")
    public String insert(@RequestParam MultipartFile[] uploadfile , Model model , QnaDTO qnaDto, QnaFileDTO qnaFileDto, FileUtil util) throws Exception {
        qnaDto.setQnaWriter((int)session.getAttribute("memberSeq"));
        qnaService.insert(qnaDto);
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
                util.saves(uploadfile,qnaPath,sysName);
            }
        }
        model.addAttribute("files", list);
        qnaFileService.insertFile(qnaFileDto);
        return "redirect:main";
    }

    @RequestMapping("detail")
    public String selectDetail(QnaDTO qnaDto, Model model , QnaFileDTO qnaFileDto, QnaCommentDTO qnaCommentDto) throws Exception {
        QnaDTO qnaDto1 = qnaService.selectDetail(qnaDto);
        QnaFileDTO qnaFileDto1 = qnaFileService.selectFile(qnaFileDto);
        List<QnaCommentDTO> qnaCommentDto1 = qnaCommentService.selectComment(qnaCommentDto);
        model.addAttribute("detail",qnaDto1);
        model.addAttribute("file",qnaFileDto1);
        model.addAttribute("comment",qnaCommentDto1);
        return "detail";
    }
// ajax

    @RequestMapping("delete")
    public String delete(int qnaSeq, FileUtil util,String qnaSysName) throws Exception{
        qnaService.delete(qnaSeq);
        qnaFileService.deleteFile(qnaSeq);
        util.delete(qnaPath,qnaSysName);
        return "redirect:main";
    }

    @RequestMapping("deleteComment")
    public String delete(int qnaSeq,int qnaCommentSeq) throws Exception{
        qnaCommentService.deleteComment(qnaSeq,qnaCommentSeq);
        return "redirect:/";
    }

    @RequestMapping("download")
    public ResponseEntity<Resource> download(FileUtil util, String sysName, String oriName) throws Exception {
        return util.download(qnaPath,sysName,oriName);
    }
}
