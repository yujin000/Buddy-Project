package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/personalFolder/")
public class PersonalFolderController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PersonalFolderService personalFolderService;

    // ExceptionHandler
    @ExceptionHandler
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 폴더 생성
    @ResponseBody
    @RequestMapping("addFolder")
    public String addFolder() throws Exception{
        // 새 폴더 중복시 파일 뒤에 붙여줄 count
        int count = 1;
        boolean folderExists = true;

        PersonalFolderDTO dto = new PersonalFolderDTO();

        // 세션에 있는 memberSeq값 폴더 dto에 입력
        dto.setPersonalFolderMemberSeq(Integer.parseInt(session.getAttribute("memberSeq").toString()));

        String realPath = session.getServletContext().getRealPath("");
        System.out.println(realPath);

        while (folderExists) {
            if(count == 1){
                File file = new File(realPath + "새 폴더");
                if (!file.exists()) {
                    file.mkdir();
                    folderExists = false;
                    dto.setPersonalFolderName("새 폴더");
                    personalFolderService.insertNewFolder(dto);
                }
                count++;
            }else{
                File file = new File(realPath + "새 폴더" + count);
                if (!file.exists()) {
                    file.mkdir();
                    folderExists = false;
                    dto.setPersonalFolderName("새 폴더" + count);
                    personalFolderService.insertNewFolder(dto);
                }
                count++;
            }


        }
        return "";
    }

}
