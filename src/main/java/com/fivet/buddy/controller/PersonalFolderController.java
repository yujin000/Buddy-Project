package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

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
    @Transactional
    @ResponseBody
    @RequestMapping("addFolder")
    public boolean addFolder(String folderName,String parentKey, String isTeam) throws Exception {
        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());

        // 폴더 중복 체크( true : 존재함, false : 존재하지 않음)
        boolean folderExists = false;
        List<Map<String,String>> nameList = personalFolderService.nameList(parentKey);

        for(Map<String,String> map : nameList){
            if(map.get("PERSONAL_FOLDER_NAME") == null){
                folderExists = false;
            }else  if(map.get("PERSONAL_FOLDER_NAME").equals(folderName)){
                folderExists = true;
                break;
            }
        };


        // 폴더가 존재하지 않는 경우( 사용이 가능한 경우 )
        if (!folderExists) {
            // 생성할 폴더 경로
            String uploadFilePath = personalFolderService.searchPath(parentKey);
            // 폴더 생성
            File file = new File(uploadFilePath + folderName);
            file.mkdir();
            // personal_folder 테이블에 insert
            personalFolderService.insertNewFolder(folderName,parentKey,uploadFilePath,memberSeq,isTeam);
            return true;
        } else {
            // 폴더가 이미 존재하는 경우( 사용이 불가능한 경우 )
            return false;
        }
    }

    // 폴더 (정보)
    @ResponseBody
    @RequestMapping("myFolderInfo")
    public PersonalFolderDTO myFolderInfo(String key) throws Exception{
        return personalFolderService.myFolderInfo(key);
    }

    // 부모 경로 찾기
    @ResponseBody
    @RequestMapping("searchPath")
    public PersonalFolderDTO searchPath(String thisFolderKey) throws Exception{
        return personalFolderService.pathAndType(thisFolderKey);
    }
}
