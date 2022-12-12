package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private MemberMapper memberMapper;

    public int test() throws Exception{
        return memberMapper.test();
    }
}
