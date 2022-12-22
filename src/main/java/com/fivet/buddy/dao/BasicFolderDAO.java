package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.BasicFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BasicFolderDAO {

    @Autowired
    private BasicFolderMapper basicFolderMapper;

    // 신규 회원가입 시 기본 폴더 생성
    public void newBasicFolder(int basicFolderMemberSeq) throws Exception{
        basicFolderMapper.newBasicFolder(basicFolderMemberSeq);
    };
}
