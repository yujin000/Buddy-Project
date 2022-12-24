package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.ChatMsgDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMsgMapper {
    void insertChatMsg(ChatMsgDTO chatMsgDto);
}
