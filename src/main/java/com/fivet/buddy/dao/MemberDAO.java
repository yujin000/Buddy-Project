package com.fivet.buddy.dao;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private MemberMapper memberMapper;

    // 회원가입 (signUp)
    public void signUp(MemberDTO memberDto) throws Exception {
        memberMapper.signUp(memberDto);
    }
}
