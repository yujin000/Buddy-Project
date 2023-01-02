package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.ChatFilesDTO;
import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMsgMapper {
    void insertChatMsg(ChatMsgDTO chatMsgDto);

    void insertFiles(ChatFilesDTO chatFilesDto);
    List<ChatMsgDTO> selectChatMsg(int chatRoomSeq);
    void delChatRoom(int chatRoomSeq);
}
