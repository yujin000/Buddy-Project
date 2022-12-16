package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.InviteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDAO {
    @Autowired
    private InviteMapper inviteMapper;

    // 코드 여부 체크
    public boolean codeCheck(String inviteCode) throws Exception{
        return inviteMapper.codeCheck(inviteCode);
    }

    // 코드 insert
    public void codeInsert(String inviteCode) throws Exception{
        inviteMapper.codeInsert(inviteCode);
    }

    // 초대코드 delete
    public void codeDelete(String inviteCode) throws Exception{
        inviteMapper.codeDelete(inviteCode);
    }
}
