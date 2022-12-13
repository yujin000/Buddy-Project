package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void signUp(MemberDTO memberDto);
    boolean isAccountExist(MemberDTO memberDto);
    MemberDTO selectAccountInfo(MemberDTO memberDto);
}
