package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatFilesDTO;
import com.fivet.buddy.mapper.ChatMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatFilesDAO {

    @Autowired
    private ChatMsgMapper chatMsgMapper;
    public void insertFiles(ChatFilesDTO chatFilesDto) throws Exception{
        chatMsgMapper.insertFiles(chatFilesDto);
    }
}
