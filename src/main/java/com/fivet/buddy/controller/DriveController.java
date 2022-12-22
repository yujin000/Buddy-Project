package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.services.PersonalFileService;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/drive/")
public class DriveController {

    @Autowired
    private PersonalFolderService personalFolderService;

    @Autowired
    private PersonalFileService personalFileService;

    @Autowired
    private HttpSession session;

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return "error";
    }

    // fileDrive.html로 이동
    @RequestMapping("toFileDrive")
    public String toFileDrive(Model model) throws Exception{
        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());

        List<PersonalFolderDTO> myFolders = personalFolderService.selectMyFolders(memberSeq);
        List<PersonalFileDTO> myFiles = personalFileService.selectMyFiles(memberSeq);
        model.addAttribute("parentKey",personalFolderService.myBasicFolder(memberSeq));
        model.addAttribute("myFolders",myFolders);
        model.addAttribute("myFiles",myFiles);
        return "drive/fileDrive";
    }

    // 폴더 세부내역으로 이동
    @RequestMapping("detailDrive")
    public String detailDrive(String resourceKey, Model model) throws Exception{
        List<PersonalFolderDTO> myFolders = personalFolderService.selectChildFolders(resourceKey);
        model.addAttribute("parentKey",resourceKey);
        model.addAttribute("myFolders",myFolders);
        return "drive/detailDrive";
    }
}
