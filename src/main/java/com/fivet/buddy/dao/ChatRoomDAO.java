package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.mapper.ChatRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ChatRoomDAO {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    //팀 생성시 기본 채팅방 개설
    public void createTeam(Map<String, String> param) {
        chatRoomMapper.createTeam(param);
    }

    //팀 입장시 좌측 사이드바에 채팅방 목록을 출력
    public List<ChatRoomDTO> chatRoomList(Map<String, Integer> param) {
        return chatRoomMapper.chatRoomList(param);
    }

    //팀 합류시(생성 포함) 기본적으로 추가되는 나와의 대화
    public void insertSelfChat(Map<String, String> param) { chatRoomMapper.insertSelfChat(param);}

}
