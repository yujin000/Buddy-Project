package com.fivet.buddy.controller;

import com.fivet.buddy.services.PersonalFileService;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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

    // 파일 첨부
    @RequestMapping("uploadFile")
    public String uploadFile(MultipartFile multipartFile, String attachFolder) throws Exception{
        String uploadFilePath = personalFolderService.searchPath(attachFolder);

        String oriName = multipartFile.getOriginalFilename();
        String sysName = UUID.randomUUID() + "_" + oriName;

        multipartFile.transferTo(new File(uploadFilePath+sysName));

        // personal_file 테이블에 insert
        personalFileService.uploadFile(oriName,sysName,attachFolder);

        return "redirect:/drive/toFileDrive";
    }
}
