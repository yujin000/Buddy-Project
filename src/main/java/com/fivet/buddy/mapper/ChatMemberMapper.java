package com.fivet.buddy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ChatMemberMapper {

    //채팅 인원 추가
    // void insertChatMember(int chatRoomSeq, int memberSeq);

    //팀 생성시 생성되는 기본 채팅방 목록에 생성자 추가
    void createTeam(Map<String, String> param);

}
