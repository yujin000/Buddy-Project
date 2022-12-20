package com.fivet.buddy.services;

import com.fivet.buddy.dao.PersonalFolderDAO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonalFolderService {

    @Autowired
    private PersonalFolderDAO personalFolderDao;

    // 새 폴더 추가
    public void insertNewFolder(PersonalFolderDTO personalFolderDto) throws Exception{
        personalFolderDao.insertNewFolder(personalFolderDto);
    }
}
