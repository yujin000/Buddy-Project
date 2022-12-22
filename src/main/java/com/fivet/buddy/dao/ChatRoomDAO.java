package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.ChatRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ChatRoomDAO {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    //팀 생성시 기본 채팅방 개설
    public void createTeam(Map<String, String> param) {
        chatRoomMapper.createTeam(param);
    }

}
