package com.fivet.buddy.dao;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.mapper.BasicFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BasicFolderDAO {

    @Autowired
    private BasicFolderMapper basicFolderMapper;

    // 신규 회원가입 시 기본 폴더 생성
    public void newBasicFolder(Map<String, Object> map) throws Exception{
        basicFolderMapper.newBasicFolder(map);
    };

    // 기본 폴더에 있는 폴더 key값 가져오기
    public String myBasicFolder(int memberSeq) throws Exception{
        return basicFolderMapper.myBasicFolder(memberSeq);
    }
}
