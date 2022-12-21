package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.mapper.ChatRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomDAO {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    //팀 생성시 기본 채팅방 개설
    public void createTeam(TeamDTO teamDto) {
        chatRoomMapper.createTeam(teamDto);
    }

}
