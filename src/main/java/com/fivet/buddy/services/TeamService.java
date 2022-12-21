package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatMemberDAO;
import com.fivet.buddy.dao.ChatRoomDAO;
import com.fivet.buddy.dao.TeamDAO;
import com.fivet.buddy.dao.TeamMemberDAO;
import com.fivet.buddy.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeamService {

    @Autowired
    private TeamDAO teamDao;

    @Autowired
    private TeamMemberDAO teamMemberDao;

    @Autowired
    private ChatRoomDAO chatRoomDao;

    @Autowired
    private ChatMemberDAO chatMemberDao;

    //팀 생성
    public void insertTeam (TeamDTO teamDto, Map<String, String> param) throws Exception {
        teamDao.insert(teamDto);
        param.put("teamSeq",String.valueOf(teamDto.getTeamSeq()));
        teamMemberDao.createTeam(param);
        chatRoomDao.createTeam(teamDto);
        //param.put("chatRoomSeq", String.valueOf(teamDto.))
        //chatMemberDao.insertChatMember();
    }

    //회원 팀 출력
    public List<TeamDTO> selectMemberTeam(int memberSeq) {
        return teamDao.selectMemberTeam(memberSeq);
    }
}
