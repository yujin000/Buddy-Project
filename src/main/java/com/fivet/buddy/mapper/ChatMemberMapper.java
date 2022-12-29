package com.fivet.buddy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ChatMemberMapper {

    //팀 합류시 기본생성되는 채팅방 목록 (생성포함)
    void insertChatMember(Map<String, String> param);

}
