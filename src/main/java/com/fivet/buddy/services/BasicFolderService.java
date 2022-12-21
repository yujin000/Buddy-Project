package com.fivet.buddy.services;

import com.fivet.buddy.dao.BasicFolderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicFolderService {

    @Autowired
    private BasicFolderDAO basicFolderDao;

    // 신규 회원가입 시 기본 폴더 생성
    public void newBasicFolder(int basicFolderMemberSeq) throws Exception{
        basicFolderDao.newBasicFolder(basicFolderMemberSeq);
    };

}
