package com.fivet.buddy.dao;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.mapper.PersonalFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalFolderDAO {

    @Autowired
    private PersonalFolderMapper personalFolderMapper;

    // 새 폴더 추가
    public void insertNewFolder(PersonalFolderDTO personalFolderDto) throws Exception{
        personalFolderMapper.insertNewFolder(personalFolderDto);
    }

}
