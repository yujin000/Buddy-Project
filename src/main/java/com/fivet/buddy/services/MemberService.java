package com.fivet.buddy.services;

import com.fivet.buddy.dao.MemberDAO;
import com.fivet.buddy.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDAO memberDao;

    // 회원가입 (signUp)
    public void signUp(MemberDTO memberDto) throws Exception{
        memberDao.signUp(memberDto);
    }

}
