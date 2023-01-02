package com.fivet.buddy.controller;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.services.*;
import com.fivet.buddy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/drive/")
public class DriveController {

    @Autowired
    private PersonalFolderService personalFolderService;

    @Autowired
    private PersonalFileService personalFileService;

    @Autowired
    private BasicFolderService basicFolderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;


    @Autowired
    private HttpSession session;

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // fileDrive.html로 이동
    @RequestMapping("toFileDrive")
    public String toFileDrive(Model model, String accessRights) throws Exception {
        // 접근 제한
        if(accessRights == null){
            model.addAttribute("access",0);
        }else{
            model.addAttribute("access",1);
        }

        int memberSeq = Integer.parseInt(session.getAttribute("memberSeq").toString());

        List<PersonalFolderDTO> myFolders = personalFolderService.selectMyFolders(memberSeq);
        List<PersonalFileDTO> myFiles = personalFileService.selectMyFiles(memberSeq);
        model.addAttribute("parentKey", personalFolderService.myBasicFolder(memberSeq));
        model.addAttribute("myFolders", myFolders);
        model.addAttribute("myFiles", myFiles);

        // 타이틀(현재 폴더 이름) 뽑기
        model.addAttribute("nowFolder", personalFolderService.nowFolder(personalFolderService.myBasicFolder(memberSeq)));

        // 전체 사용 용량
        long myVolume = basicFolderService.myVolume(memberSeq);
        model.addAttribute("myVolume",myVolume);

        // 팀 리스트
        List <TeamDTO> teamDtoList = teamService.selectMemberTeam((int)session.getAttribute("memberSeq"));

        List<Map<String,Integer>> teamSeqList = new ArrayList<>();

        for(TeamDTO teamDto : teamDtoList){
            Map<String, Integer> map = new HashMap<>();
            map.put("seq",teamDto.getTeamSeq());
            teamSeqList.add(map);
        }

        List<PersonalFolderDTO> teamFolderList = personalFolderService.getRootTeamFolder(teamSeqList);
        model.addAttribute("teamList",teamFolderList);

        return "drive/fileDrive";
    }

    // 폴더 세부내역으로 이동
    @RequestMapping("detailDrive")
    public String detailDrive(String resourceKey,String teamAccess, Model model) throws Exception {
        int memberSeq = (Integer)session.getAttribute("memberSeq");

        // 공유받은 폴더인지 확인
        PersonalFolderDTO checkOwner = personalFolderService.getFolderOwner(resourceKey);
        String ownerName = memberService.getOwnerName(checkOwner.getPersonalFolderMemberSeq());

        if(teamAccess == null){
            if(checkOwner.getPersonalFolderShared().equals("N") && memberSeq != checkOwner.getPersonalFolderMemberSeq()) {
                return "redirect:/drive/toFileDrive?accessRights=false";
            }
        }

        if(teamAccess == null){
            if(memberSeq != checkOwner.getPersonalFolderMemberSeq()){
                model.addAttribute("ownerName",ownerName);
                model.addAttribute("shared",1);
            }else{
                model.addAttribute("shared",0);
            }
        }else if(teamAccess.equals("true")){
            model.addAttribute("shared",0);
        }

        // 전체
        List<PersonalFolderDTO> myFolders = personalFolderService.selectMyFolders(memberSeq);
        List<PersonalFileDTO> myFiles = personalFileService.selectMyFiles(memberSeq);
        model.addAttribute("myFolders", myFolders);
        model.addAttribute("myFiles", myFiles);

        // 하위폴더
        List<PersonalFolderDTO> childFolders = personalFolderService.selectChildFolders(resourceKey);
        List<PersonalFileDTO> childFiles = personalFileService.selectChildFiles(resourceKey);
        model.addAttribute("parentKey", resourceKey);
        model.addAttribute("childFolders", childFolders);
        model.addAttribute("childFiles", childFiles);


        // 팀 폴더 여부
        boolean isTeam = personalFolderService.isTeam(resourceKey);
        String resultToString = String.valueOf(isTeam);
        model.addAttribute("isTeam",resultToString);


        // 타이틀(현재 폴더 이름) 뽑기
        model.addAttribute("nowFolder", personalFolderService.nowFolder(resourceKey));

        // 팀 리스트
        List <TeamDTO> teamDtoList = teamService.selectMemberTeam((int)session.getAttribute("memberSeq"));

        List<Map<String,Integer>> teamSeqList = new ArrayList<>();

        for(TeamDTO teamDto : teamDtoList){
            Map<String, Integer> map = new HashMap<>();
            map.put("seq",teamDto.getTeamSeq());
            teamSeqList.add(map);
        }

        List<PersonalFolderDTO> teamFolderList = personalFolderService.getRootTeamFolder(teamSeqList);
        model.addAttribute("teamList",teamFolderList);

        // 팀 / 개인 사용 용량
        if(!isTeam){
            long myVolume = basicFolderService.myVolume(memberSeq);
            model.addAttribute("myVolume",myVolume);
        }
        else{
            int folderTeamSeq;
            String searchKey = resourceKey;

            while(true){
                folderTeamSeq = personalFolderService.getTeamSeq(searchKey);
                if(folderTeamSeq == 0){
                    searchKey = personalFolderService.getParentKey(searchKey);
                }else{
                    long teamRootVolume = basicFolderService.getTeamVolume(personalFolderService.getRootTeamKey(folderTeamSeq));
                    model.addAttribute("teamVolume",teamRootVolume);
                    break;
                }
            }
        }

        return "drive/detailDrive";
    }

    // 파일 삭제하기
    @ResponseBody
    @RequestMapping("delete")
    public void delete(@RequestBody List<Map<String, String>> deleteList, String parentKey) throws Exception {

        boolean folderDelete = false;
        boolean fileDelete = false;

        List<Map<String, String>> folders = new ArrayList<>();
        List<Map<String, String>> files = new ArrayList<>();

        for (Map<String, String> map : deleteList) {
            String type = map.get("type");
            String key = map.get("key");
            String name = map.get("name");
            String folderKey = map.get("folder");

            if (type.equals("folder")) {
                Map<String, String> folder = new HashMap<>();
                folder.put("key", key);
                folder.put("name", name);
                folder.put("memberSeq",session.getAttribute("memberSeq").toString());
                folders.add(folder);
                folderDelete = true;
            } else {
                Map<String, String> file = new HashMap<>();
                file.put("key", key);
                file.put("name", name);
                file.put("folderKey",folderKey);
                file.put("memberSeq",session.getAttribute("memberSeq").toString());
                files.add(file);
                fileDelete = true;
            }
        }

        if (folderDelete && fileDelete) {
            personalFolderService.deleteFolder(folders);
            personalFileService.deleteFile(files, parentKey);
        } else if (fileDelete && !folderDelete) {
            personalFileService.deleteFile(files, parentKey);
        } else {
            personalFolderService.deleteFolder(folders);
        }

    }

    // 하위 폴더들 불러오기
    @ResponseBody
    @RequestMapping("getKey")
    public List<PersonalFolderDTO> childFolders(String parentFolderKey) throws Exception {
        List<PersonalFolderDTO> result = personalFolderService.selectChildFolders(parentFolderKey);
        return result;
    }

    // 폴더 밑에 하위폴더가 있는지 확인
    @ResponseBody
    @RequestMapping("subIsExist")
    public boolean subIsExist(String folderKey) throws Exception {
        return personalFolderService.subIsExist(folderKey);
    }

    // 파일 및 폴더 다운로드하기
    @ResponseBody
    @RequestMapping("download")
    public ResponseEntity<Resource> download(@RequestBody List<Map<String, String>> downloadList, FileUtil fileUtil) throws Exception {

        for (Map<String, String> map : downloadList) {
            String type = map.get("type");
            String key = map.get("key");

            if (type.equals("folder")) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ZipOutputStream zos = new ZipOutputStream(baos);
                File folder = new File(personalFolderService.myPath(key));
                fileUtil.addFolderToZip(zos, folder);
                zos.close();
                Resource resource = new ByteArrayResource(baos.toByteArray());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=downloaded.zip")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);

            } else {
                List<Map<String, String>> files = new ArrayList<>();
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("key", key);
                files.add(fileMap);
                return fileUtil.downloadByPath(personalFileService.getFilePath(files).get(0).get("path"), personalFileService.getFilePath(files).get(0).get("oriName"));
            }
        }

        return null;
    }

    // 파일 공유 (실행 / 중단)
    @ResponseBody
    @RequestMapping("access")
    public String accessStatus(String access,String key) throws Exception{
        personalFolderService.accessStatus(access,key);
        return null;
    }

    // 팀인지 개인인지 확인
    @ResponseBody
    @RequestMapping("isTeam")
    public boolean isTeam(String parentKey) throws Exception{
        return personalFolderService.isTeam(parentKey);
    }

    // 전체 기본폴더 key 출력
    @ResponseBody
    @RequestMapping("allKeys")
    public List<Map<String,String>> allBasicKey() throws Exception{
        return basicFolderService.allBasicKey();
    }
}