package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.mapper.ChatMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMsgDAO {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    // 채팅방 메세지 입력
    public void insertChatMsg(ChatMsgDTO chatMsgDto) {
        chatMsgMapper.insertChatMsg(chatMsgDto);
    }

    // 채팅방 메세지 출력
    public List<ChatMsgDTO> selectChatMsg(int chatRoomSeq) {
        return chatMsgMapper.selectChatMsg(chatRoomSeq);
    }

    // 채팅방 삭제에 따른 메세지 전체 삭제
    public void delChatRoom(int chatRoomSeq) { chatMsgMapper.delChatRoom(chatRoomSeq);}

    // 특정 회원이 1명뿐인 팀의 채팅방 내역을 모두 삭제.
    public void delOnlyOneMsg(int memberSeq) {
        chatMsgMapper.delOnlyOneMsg(memberSeq);
    }
}
