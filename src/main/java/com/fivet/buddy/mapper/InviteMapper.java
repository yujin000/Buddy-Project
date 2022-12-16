package com.fivet.buddy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InviteMapper {

    boolean codeCheck(String inviteCode);

    void codeInsert(String inviteCode);

    void codeDelete(String inviteCode);
}
