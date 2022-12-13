package com.fivet.buddy.services;

import com.fivet.buddy.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDAO memberDao;

    public int test() throws Exception{
        return memberDao.test();
    }
}
