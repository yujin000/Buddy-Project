package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int test();
    void Signup(MemberDTO memberDto);

    void signUp(MemberDTO memberDto);
}
