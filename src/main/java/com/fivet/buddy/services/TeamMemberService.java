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

    public TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto) {
        return teamMemberDao.selectOne(teamMemberDto);
    }

    public List<TeamMemberDTO> selectChatMember(int chatRoomSeq) {
        return teamMemberDao.selectChatMember(chatRoomSeq);
    }

}
