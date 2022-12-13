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

    // 로그인 시 아이디 있는지 체크
    public boolean isAccountExist(MemberDTO memberDto) throws Exception{
        return memberDao.isAccountExist(memberDto);
    }

    // 로그인 시 정보 불러오기
    public MemberDTO selectAccountInfo(MemberDTO memberDto) throws Exception{
        return memberDao.selectAccountInfo(memberDto);
    }
}
