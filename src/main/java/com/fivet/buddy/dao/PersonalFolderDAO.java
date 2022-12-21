package com.fivet.buddy.dao;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.mapper.PersonalFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PersonalFolderDAO {

    @Autowired
    private PersonalFolderMapper personalFolderMapper;


    // (개인)회원 별 폴더 불러오기
    public List<PersonalFolderDTO> selectMyFolders(String memberSeq) throws Exception{
        return personalFolderMapper.selectMyFolders(memberSeq);
    }

    // 새 폴더 추가
    public void insertNewFolder(PersonalFolderDTO personalFolderDto) throws Exception{
        personalFolderMapper.insertNewFolder(personalFolderDto);
    }

    // 폴더 존재 유무 체크
    public boolean isFolderExists(Map<String, Object> folderCheck) throws Exception{
        return personalFolderMapper.isFolderExists(folderCheck);
    }

}
