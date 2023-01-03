package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatMemberDAO;
import com.fivet.buddy.dao.ChatRoomDAO;
import com.fivet.buddy.dto.ChatMemberDTO;
import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.ChatMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    // 토픽 생성
    public ChatRoomDTO insertTopic(TeamDTO teamDto, ChatRoomDTO chatRoomDto) {
        chatRoomDto.setMemberCount(teamDto.getTeamCount());
        chatRoomDto.setTeamSeq(teamDto.getTeamSeq());
        chatRoomDto.setRoomOwnerSeq(teamDto.getTeamOwnerSeq());
        chatRoomDao.insertTopic(chatRoomDto);
        return chatRoomDto;
    }

    // 토픽 리스트 출력
    public List<ChatRoomDTO> selectTopic(int teamSeq) {
        return chatRoomDao.selectTopic(teamSeq);
    }

    // 토픽 카운트
    public int countTopic(int teamSeq) {
        return chatRoomDao.countTopic(teamSeq);
    }

    // 일반 채팅방 생성
    public void insertNormalChat (ChatRoomDTO chatRoomDto) { chatRoomDao.insertNormalChat(chatRoomDto); }

    // 채팅방 삭제
    public void delChatRoom (int chatRoomSeq) { chatRoomDao.delChatRoom(chatRoomSeq); }

    // 채팅방 이름 변경
    public void updateChatTitle (ChatRoomDTO chatRoomDto) { chatRoomDao.updateChatTitle(chatRoomDto); }

    // 채팅방에서 이탈시, 회원수를 1 감소.
    public void delChatMember (int chatRoomSeq) { chatRoomDao.delChatMember(chatRoomSeq);}

    //인원수가 0명인 채팅방 삭제
    public void delChatRoomCountZero() { chatRoomDao.delChatRoomCountZero(); }

    // 팀원이 팀탈퇴시, 채팅방 목록에서 팀원이 삭제되며,
    // 그에 따라 방 인원수도 1씩 감소해야함.
    // 또한, 방 인원수가 0이된 방은 제거해준다.
    public void teamSelfOut(TeamMemberDTO teamMemberDto) {
        List<ChatMemberDTO> chatMemberList = chatMemberDao.selectMemberChatList(teamMemberDto);
        Map<String, Integer> param = new HashMap<>();
        param.put("teamSeq", teamMemberDto.getTeamSeq());
        param.put("memberSeq", teamMemberDto.getMemberSeq());
        chatMemberDao.delTeamChatMember(param);
        // 각 방의 인원수 1씩 감소
        for (ChatMemberDTO dto : chatMemberList) {
            chatRoomDao.delChatMember(dto.getChatRoomSeq());
        }
        chatRoomDao.delChatRoomCountZero();
    }

}
