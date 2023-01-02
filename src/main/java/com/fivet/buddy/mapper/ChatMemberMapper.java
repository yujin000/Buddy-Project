package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.ChatMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMemberMapper {

    //팀 합류시 기본생성되는 채팅방 목록 (생성포함)
    void insertChatMember(Map<String, String> param);
    //채팅방 멤버 출력
    List<ChatMemberDTO> selectChatMember(int chatRoomSeq);
    void delChatRoom(int chatRoomSeq);
    void delChatMember(ChatMemberDTO chatMemberDto);
}
