package com.fivet.buddy.dao;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.mapper.MemberMapper;
import org.apache.ibatis.executor.ExecutionPlaceholder;
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

    // 로그인 시 아이디 있는지 체크
    public boolean isAccountExist(MemberDTO memberDto) throws  Exception{
        return memberMapper.isAccountExist(memberDto);
    }

    // 로그인 시 정보 불러오기
    public MemberDTO selectAccountInfo(MemberDTO memberDto) throws Exception{
        return memberMapper.selectAccountInfo(memberDto);
    }
}
