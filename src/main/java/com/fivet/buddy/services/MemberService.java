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

    // 회원가입 시 아이디(이메일) 중복체크
    public boolean idCheck(String id) throws Exception{
        return memberDao.idCheck(id);
    }

    // 로그인 시 아이디 있는지 체크
    public boolean isAccountExist(MemberDTO memberDto) throws Exception{
        return memberDao.isAccountExist(memberDto);
    }

    // 로그인 시 정보 불러오기
    public MemberDTO selectAccountInfo(MemberDTO memberDto) throws Exception{
        return memberDao.selectAccountInfo(memberDto);
    }

    // 로그인 시 정보 불러오기(네이버/카카오)
    public MemberDTO selectAccountInfoForNK(MemberDTO memberDto) throws Exception{
        return memberDao.selectAccountInfoForNK(memberDto);
    }

    // 카카오 로그인 시 아이디 있는지 체크
    public boolean isKakaoExist(MemberDTO memberDto) throws Exception{
        return memberDao.isKakaoExist(memberDto);
    }

    // 네이버 로그인 시 아이디 있는지 체크
    public boolean isNaverExist(MemberDTO memberDto) throws Exception{
        return memberDao.isNaverExist(memberDto);
    }

    // 로그인 상태에서 로그인페이지 접근 차단
    public MemberDTO selectMyInfo(String memberSeq) throws Exception{
        return memberDao.selectMyInfo(memberSeq);
    }

    //계정설정으로 이동
    public MemberDTO selectMyProfile(String memberSeq) throws Exception{
        return memberDao.selectMyProfile(memberSeq);
    }

    //휴대전화 수정
    public void updatePhone(MemberDTO memberDto) throws Exception{
        memberDao.updatePhone(memberDto);
    }

    //현재비밀번호 일치여부
    public boolean selectMyProfilePw(MemberDTO memberDto) throws Exception{
        if(memberDao.selectMyProfilePw(memberDto)==1){
            return true;
        }else{
            return false;
        }
    }

    //비밀번호 수정
    public void updatePw(MemberDTO memberDto) throws Exception{
        memberDao.updatePw(memberDto);
    }

}
