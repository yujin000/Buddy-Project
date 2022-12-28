package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.ChatMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ChatMemberDAO {

    @Autowired
    private ChatMemberMapper chatMemberMapper;

    // 채팅방 멤버 추가
    public void insertChatMember(Map<String, String> param) {
        chatMemberMapper.insertChatMember(param);
    }
}
