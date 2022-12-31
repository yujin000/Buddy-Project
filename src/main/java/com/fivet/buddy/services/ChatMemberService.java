package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatMemberDAO;
import com.fivet.buddy.dao.TeamMemberDAO;
import com.fivet.buddy.dto.ChatMemberDTO;
import com.fivet.buddy.dto.ChatRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatMemberService {

    @Autowired
    TeamMemberDAO teamMemberDao;

    @Autowired
    ChatMemberDAO chatMemberDao;

    // 생성된 토픽에 팀 회원 입력
    public void insertTopicMember (ChatRoomDTO chatRoomDto) {
        List<Integer> teamMemberSeqList = teamMemberDao.selectTeamMemberSeq(chatRoomDto.getTeamSeq());
        Map<String, String> param = new HashMap<>();
        param.put("chatRoomSeq", String.valueOf(chatRoomDto.getChatRoomSeq()));
        param.put("teamSeq", String.valueOf(chatRoomDto.getTeamSeq()));

        for (int i : teamMemberSeqList) {
            param.put("memberSeq", String.valueOf(i));
            chatMemberDao.insertChatMember(param);
        }
    }
}
