package com.fivet.buddy.services;

import com.fivet.buddy.dao.BasicFolderDAO;
import com.fivet.buddy.dao.PersonalFileDAO;
import com.fivet.buddy.dao.PersonalFolderDAO;
import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.util.FileUtil;
import com.fivet.buddy.util.RandomKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalFileService {

    @Autowired
    private PersonalFileDAO personalFileDao;

    @Autowired
    private PersonalFolderDAO personalFolderDao;

    @Autowired
    private RandomKeyUtil randomKeyUtil;

    @Autowired
    private BasicFolderDAO basicFolderDao;

    // 파일첨부
    public void uploadFile(String oriName, String sysName, String attachFolder, int memberSeq, String filePath, long fileSize) throws Exception{
        PersonalFileDTO personalFileDto = new PersonalFileDTO();

        personalFileDto.setPersonalFilesMemberSeq(memberSeq);
        personalFileDto.setPersonalFilesOriname(oriName);
        personalFileDto.setPersonalFilesSysname(sysName);
        personalFileDto.setPersonalFilesFolderKey(attachFolder);
        personalFileDto.setPersonalFilesKey(randomKeyUtil.folderKey());
        personalFileDto.setPersonalFilesPath(filePath);
        personalFileDto.setPersonalFilesByte(fileSize);
        personalFileDao.uploadFile(personalFileDto);
    }

    // 내 파일 찾기
    public List<PersonalFileDTO> selectMyFiles(int memberSeq) throws Exception{
        String basicKey = basicFolderDao.myBasicFolder(memberSeq);
        Map<String, Object> map = new HashMap<>();
        map.put("memberSeq",memberSeq);
        map.put("basicKey",basicKey);
        return personalFileDao.selectMyFiles(map);
    }

    // 하위 폴더 파일 찾기
    public List<PersonalFileDTO> selectChildFiles(String resourceKey) throws Exception{
        return personalFileDao.selectChildFiles(resourceKey);
    }

    // 경로로 파일 삭제
    public void deleteFile(List<Map<String, String>> files, String parentKey) throws Exception {
        FileUtil fileUtil = new FileUtil();


        // 경로 불러오기
        String path = personalFileDao.myPath(parentKey);
        // 실제 경로에서 삭제
        for(Map<String, String> map : files){
            // 삭제 보낼 Map
            Map<String,String> sendMap = new HashMap<>();
            String name = map.get("name");
            sendMap.put("key",map.get("key"));
            sendMap.put("memberSeq",map.get("memberSeq"));
            sendMap.put("folderKey",map.get("folderKey"));
            basicFolderDao.deleteFileByte(sendMap);
            personalFolderDao.deleteFileByte(sendMap);

            fileUtil.delete(path,name);
        }

        personalFileDao.deleteFile(files);
    }

    public List<Map<String, String>> getFilePath(List<Map<String, String>> files) {
        return personalFileDao.getFilePath(files);
    }

    // oriName
    public String thisOriName(String key) {
        return personalFileDao.thisOriName(key);
    }

    // 폴더 경로 찾기
    public String searchPath(String key) {
        return personalFileDao.searchPath(key);
    }

    // 파일 정보 불러오기
    public PersonalFileDTO myFileInfo(String key) {
        return personalFileDao.myFileInfo(key);
    }

    // 회원 sequence로 파일 삭제
    public void memberOut(int memberSeq) throws Exception{
        personalFileDao.memberOut(memberSeq);
    }
}
