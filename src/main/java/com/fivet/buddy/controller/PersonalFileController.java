package com.fivet.buddy.controller;

import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/personalFile/")
public class PersonalFileController {

    @Autowired
    private PersonalFolderService personalFolderService;

    // 나중에 AWS 열면 경로 변경할 것(application.properties 에서)
    //@Value("${spring.servlet.multipart.location}")
    private String uploadFilePath;


    @RequestMapping("uploadFromBtn")
    public String uploadFromBtn(MultipartFile multipartFile) throws Exception{

        File filePath = new File(uploadFilePath);

        if(!filePath.exists()) {
            filePath.mkdir();
        }

        String oriName = multipartFile.getOriginalFilename();
        String sysName = UUID.randomUUID() + "_" + oriName;

        multipartFile.transferTo(new File(filePath+"/"+sysName));

        // personal_file 테이블에 insert
        //personalFolderService.uploadFromBtn(oriName,sysName);

        return "redirect:/drive/toFileDrive";
    }
}
