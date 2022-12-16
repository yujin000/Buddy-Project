package com.fivet.buddy.services;

import com.fivet.buddy.dao.InviteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    private InviteDAO inviteDao;

    // 초대코드 여부 체크
    public boolean codeCheck(String inviteCode) throws Exception{
        return inviteDao.codeCheck(inviteCode);
    }

    // 초대코드 insert
    public void codeInsert(String inviteCode) throws Exception{
        inviteDao.codeInsert(inviteCode);
    }

    // 초대코드 delete
    public void codeDelete(String inviteCode) throws Exception{
        inviteDao.codeDelete(inviteCode);
    }
}
