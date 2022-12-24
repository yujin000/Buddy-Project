package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.mapper.ChatMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMsgDAO {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    public void insertChatMsg(ChatMsgDTO chatMsgDto) {
        chatMsgMapper.insertChatMsg(chatMsgDto);
    }
}
