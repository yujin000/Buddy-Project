package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.ChatMemberDTO;
import com.fivet.buddy.dto.ChatMemberListDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMemberMapper {

    //팀 합류시 기본생성되는 채팅방 목록 (생성포함)
    void insertChatMember(Map<String, String> param);
    //채팅방 멤버 출력
    List<ChatMemberListDTO> selectChatMember(ChatMemberListDTO chatMemberListDto);
    void delChatRoom(int chatRoomSeq);
    void delChatMember(ChatMemberDTO chatMemberDto);
    //채팅방 멤버 프로필 이미지
    String selectChatMemberImg(int memberSeq, int chatRoomSeq);
    List<ChatMemberDTO> selectMemberChatList(TeamMemberDTO teamMemberDto);
    void delTeamChatMember(Map<String, Integer> param);
    void delOnlyOneChatMember(int memberSeq);

}
