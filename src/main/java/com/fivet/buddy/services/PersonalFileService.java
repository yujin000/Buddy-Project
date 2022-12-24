package com.fivet.buddy.services;

import com.fivet.buddy.dao.BasicFolderDAO;
import com.fivet.buddy.dao.PersonalFileDAO;
import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.util.RandomKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalFileService {

    @Autowired
    private PersonalFileDAO personalFileDao;

    @Autowired
    private RandomKeyUtil randomKeyUtil;

    @Autowired
    private BasicFolderDAO basicFolderDao;

    // 파일첨부
    public void uploadFile(String oriName, String sysName,String attachFolder,int memberSeq) throws Exception{
        PersonalFileDTO personalFileDto = new PersonalFileDTO();

        personalFileDto.setPersonalFilesMemberSeq(memberSeq);
        personalFileDto.setPersonalFilesOriname(oriName);
        personalFileDto.setPersonalFilesSysname(sysName);
        personalFileDto.setPersonalFilesFolderKey(attachFolder);
        personalFileDto.setPersonalFilesKey(randomKeyUtil.folderKey());
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
}
