package com.fivet.buddy.dao;

import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.mapper.ChatMemberMapper;
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

    //팀 합류시 기본채팅방 및 나만의 대화 채팅방 번호를 출력
    public String selectChatRoomSeq(Map<String, String> param) {
        return chatRoomMapper.selectChatRoomSeq(param);
    }

    // 채팅방에 새로운 인원 입장시, 채팅방 인원수 1 증가.
    public void updatePlusMemberCount(Map<String, String> param) { chatRoomMapper.updatePlusMemberCount(param); }

    // 토픽 생성
    public void insertTopic(ChatRoomDTO chatRoomDto) {
        chatRoomMapper.insertTopic(chatRoomDto);
    }

    // 토픽 출력
    public List<ChatRoomDTO> selectTopic(int teamSeq) {
        return chatRoomMapper.selectTopic(teamSeq);
    }

    // 토픽 카운트
    public int countTopic(int teamSeq) {
        return chatRoomMapper.countTopic(teamSeq);
    }

    // 일반채팅방 개설
    public void insertNormalChat(ChatRoomDTO chatRoomDto) { chatRoomMapper.insertNormalChat(chatRoomDto); }
    public void delChatRoom(int chatRoomSeq) { chatRoomMapper.delChatRoom(chatRoomSeq);}
}
