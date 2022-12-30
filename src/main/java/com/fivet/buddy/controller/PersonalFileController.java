package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.services.BasicFolderService;
import com.fivet.buddy.services.PersonalFileService;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/personalFile/")
public class PersonalFileController {

    @ExceptionHandler
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return "error";
    }

    @Autowired
    private PersonalFileService personalFileService;

    @Autowired
    private PersonalFolderService personalFolderService;

    @Autowired
    private BasicFolderService basicFolderService;
    @Autowired
    private HttpSession session;

    // 파일 첨부
    @RequestMapping("uploadFile")
    public String uploadFile(MultipartFile multipartFile, String attachFolder) throws Exception{
        long fileSize = multipartFile.getSize();
        String uploadFilePath = personalFolderService.searchPath(attachFolder);

        String oriName = multipartFile.getOriginalFilename();
        String sysName = UUID.randomUUID() + "_" + oriName;
        String filePath = uploadFilePath+sysName;

        multipartFile.transferTo(new File(filePath));

        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());
        // personal_file 테이블에 insert
        personalFileService.uploadFile(oriName,sysName,attachFolder,memberSeq,filePath,fileSize);
        // basic_folder 테이블에 update
        basicFolderService.uploadByte(memberSeq,fileSize);
        // personal_folder 테이블에 update
        personalFolderService.updateMyFolderByte(attachFolder,fileSize);

        return "redirect:/drive/toFileDrive";
    }

    // 파일 정보
    @ResponseBody
    @RequestMapping("myFileInfo")
    public PersonalFileDTO myFileInfo(String key) throws Exception{
        return personalFileService.myFileInfo(key);
    }
}
