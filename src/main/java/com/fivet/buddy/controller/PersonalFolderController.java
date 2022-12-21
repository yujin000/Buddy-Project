package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/personalFolder/")
public class PersonalFolderController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonalFolderService personalFolderService;

    // 나중에 AWS 열면 경로 변경할 것(application.properties 에서)
    //@Value("${spring.servlet.multipart.location}")
    private String uploadFilePath;

    // ExceptionHandler
    @ExceptionHandler
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 폴더 생성
    @ResponseBody
    @RequestMapping("addFolder")
    public boolean addFolder(String fileName) throws Exception {
        // 폴더 중복 체크( true : 존재함, false : 존재하지 않음)
        boolean folderExists = personalFolderService.isFolderExists(fileName);

        // 폴더가 존재하지 않는 경우( 사용이 가능한 경우 )
        if (!folderExists) {
            PersonalFolderDTO personalFolderDto = new PersonalFolderDTO();

            // 세션에 있는 memberSeq값 폴더 dto에 입력
            personalFolderDto.setPersonalFolderMemberSeq(Integer.parseInt(session.getAttribute("memberSeq").toString()));
            // 폴더 생성
            File file = new File(uploadFilePath + fileName);
            file.mkdir();

            personalFolderDto.setPersonalFolderName(fileName);

            // personal_folder 테이블에 insert
            personalFolderService.insertNewFolder(personalFolderDto);
            return true;
        } else {
            // 폴더가 이미 존재하는 경우( 사용이 불가능한 경우)
            return false;
        }
    }


}
