package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void signUp(MemberDTO memberDto);
    boolean idCheck(String id);
    boolean isAccountExist(MemberDTO memberDto);
    boolean isKakaoExist(MemberDTO memberDto);
    boolean isNaverExist(MemberDTO memberDto);
    MemberDTO selectAccountInfo(MemberDTO memberDto);
    MemberDTO selectAccountInfoForNK(MemberDTO memberDto);
    MemberDTO selectMyInfo(String memberSeq);
    MemberDTO selectMyProfile(String memberSeq);
    void updatePhone(MemberDTO memberDto);
    int selectMyProfilePw(MemberDTO memberDto);
    void updatePw(MemberDTO memberDto);
    void deleteMember(String memberSeq);
    List<MemberDTO> selectMembers();
    List<MemberDTO> memberSearch(String searchPick, String memberSearchText);
    void memberKickOut(int memberSeq);
}
