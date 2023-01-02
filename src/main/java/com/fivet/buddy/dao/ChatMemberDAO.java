package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatMemberDTO;
import com.fivet.buddy.mapper.ChatMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ChatMemberDAO {

    @Autowired
    private ChatMemberMapper chatMemberMapper;

    // 채팅방 멤버 추가
    public void insertChatMember(Map<String, String> param) {
        chatMemberMapper.insertChatMember(param);
    }

    //채팅방 멤버 출력
    public List<ChatMemberDTO> selectChatMember(int chatRoomSeq){
        return chatMemberMapper.selectChatMember(chatRoomSeq);
    }

    //채팅방 멤버 프로필 이미지
    public String selectChatMemberImg(int memberSeq, int chatRoomSeq){
        return chatMemberMapper.selectChatMemberImg(memberSeq,chatRoomSeq);
    }
}
