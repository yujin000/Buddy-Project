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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

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
        model.addAttribute("qna", qnaDto);
        model.addAttribute("notice", noticeDto);
        return "customer/customer";
    }

    @RequestMapping("insert")
    public String insert(@RequestParam MultipartFile[] uploadfile , Model model , QnaDTO qnaDto, QnaFileDTO qnaFileDto, FileUtil util) throws Exception {
        qnaDto.setQnaWriter((int)session.getAttribute("memberSeq"));
        System.out.println(uploadfile[0]);
        if(uploadfile[0].isEmpty()) {
            qnaService.insert(qnaDto);
        }else {
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
                    util.saves(uploadfile, qnaPath, sysName);
                }
            }
            model.addAttribute("files", list);
            qnaFileService.insertFile(qnaFileDto);
        }
        return "redirect:main";
    }

    @ResponseBody
    @PostMapping(value = "detail")
    public List<Map<String,String>> selectDetail(int qnaSeq) throws Exception {
        System.out.println(qnaSeq);
        List<QnaFileDTO> qnaFileDto = qnaFileService.selectFile(qnaSeq);
        List<QnaCommentDTO> qnaCommentDto = qnaCommentService.selectComment(qnaSeq);

        List<Map<String,String>> list = new ArrayList<>();
        for(int i = 0; i<qnaCommentDto.size();i++){
            Map<String, String> map = new HashMap<>();
            map.put("qnaCommentContents" , qnaCommentDto.get(i).getQnaCommentContents());
            System.out.println(map.get("qnaCommentContents"));
            list.add(map);
        }
        for(int i = 0; i<qnaFileDto.size();i++){
            Map<String, String> map = new HashMap<>();
            map.put("qnaOriName" , qnaFileDto.get(i).getQnaOriName());
            map.put("qnaSysName", qnaFileDto.get(i).getQnaSysName());
            System.out.println(map.get("qnaOriName"));
            list.add(map);
        }
        return list;
    }
// ajax

    @RequestMapping("delete")
    public String delete(int qnaSeq, FileUtil util,String qnaSysName) throws Exception{
        if(qnaSysName == null) {
            qnaService.delete(qnaSeq);
        }else {
            qnaService.delete(qnaSeq);
            qnaFileService.deleteFile(qnaSeq);
            util.delete(qnaPath, qnaSysName);
        }
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
