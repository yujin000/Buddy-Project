package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatMemberDAO;
import com.fivet.buddy.dao.ChatRoomDAO;
import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.ChatMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomDAO chatRoomDao;

    @Autowired
    private ChatMemberDAO chatMemberDao;

    //팀 입장시 좌측 사이드바에 채팅방 목록을 출력
    public List<ChatRoomDTO> chatRoomList(Map<String, Integer> param) {
        return chatRoomDao.chatRoomList(param);
    }

    //팀 합류시 기본채팅방 및 나만의 대화 채팅방 번호를 출력
    public void addBasicAndSelfChat(TeamMemberDTO teamMemberDto, Map<String, String> param) {
        param.put("teamSeq", String.valueOf(teamMemberDto.getTeamSeq()));

        //기본채팅방 추가
        param.put("chatType", "basic");
        param.put("chatRoomSeq", chatRoomDao.selectChatRoomSeq(param));
        chatMemberDao.insertChatMember(param);
        chatRoomDao.updatePlusMemberCount(param);
        //나와의 채팅 개설 및 추가
        param.put("chatType", "self");
        param.put("chatRoomSeq", chatRoomDao.selectChatRoomSeq(param));
        chatRoomDao.insertSelfChat(param);
        chatMemberDao.insertChatMember(param);
    }

}
