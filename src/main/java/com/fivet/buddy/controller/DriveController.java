package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.services.PersonalFileService;
import com.fivet.buddy.services.PersonalFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 타이틀(현재 폴더 이름) 뽑기
        model.addAttribute("nowFolder",personalFolderService.nowFolder(personalFolderService.myBasicFolder(memberSeq)));
        return "drive/fileDrive";
    }

    // 폴더 세부내역으로 이동
    @RequestMapping("detailDrive")
    public String detailDrive(String resourceKey, Model model) throws Exception{
        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());
        // 전체
        List<PersonalFolderDTO> myFolders = personalFolderService.selectMyFolders(memberSeq);
        List<PersonalFileDTO> myFiles = personalFileService.selectMyFiles(memberSeq);
        model.addAttribute("myFolders",myFolders);
        model.addAttribute("myFiles",myFiles);

        // 하위폴더
        List<PersonalFolderDTO> childFolders = personalFolderService.selectChildFolders(resourceKey);
        List<PersonalFileDTO> childFiles = personalFileService.selectChildFiles(resourceKey);
        model.addAttribute("parentKey",resourceKey);
        model.addAttribute("childFolders",childFolders);
        model.addAttribute("childFiles",childFiles);

        // 타이틀(현재 폴더 이름) 뽑기
        model.addAttribute("nowFolder",personalFolderService.nowFolder(resourceKey));
        return "drive/detailDrive";
    }


    // 파일 삭제하기
    @ResponseBody
    @RequestMapping("delete")
    public void delete(@RequestBody List<Map<String, String>> deleteList,String parentKey) throws Exception{

        boolean folderDelete = false;
        boolean fileDelete = false;

        List<Map<String, String>> folders = new ArrayList<>();
        List<Map<String, String>> files = new ArrayList<>();

        for(Map<String, String> map : deleteList) {
            String type = map.get("type");
            String key = map.get("key");
            String name = map.get("name");

            if (type.equals("folder")) {
                Map<String, String> folder = new HashMap<>();
                folder.put("key", key);
                folder.put("name",name);
                folders.add(folder);
                folderDelete = true;
            } else {
                Map<String, String> file = new HashMap<>();
                file.put("key", key);
                file.put("name",name);
                files.add(file);
                fileDelete = true;
            }
        }

        if(folderDelete && fileDelete){
            personalFolderService.deleteFolder(folders);
            personalFileService.deleteFile(files,parentKey);
        }else if(fileDelete && !folderDelete){
            personalFileService.deleteFile(files,parentKey);
        }else{
            personalFolderService.deleteFolder(folders);
        }

    }
}
