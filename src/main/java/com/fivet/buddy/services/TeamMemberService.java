package com.fivet.buddy.services;

import com.fivet.buddy.dao.TeamMemberDAO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamMemberService {

    @Autowired
    private TeamMemberDAO teamMemberDao;

    //회원 번호를 이용하여 해당 팀 관련 회원정보 출력.
    public TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto) {
        return teamMemberDao.selectOne(teamMemberDto);
    }

    //채팅방 참여자 목록을 출력.
    public List<TeamMemberDTO> selectChatMember(int chatRoomSeq) {
        return teamMemberDao.selectChatMember(chatRoomSeq);
    }

}
