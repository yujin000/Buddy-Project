package com.fivet.buddy.services;

import com.fivet.buddy.dao.TeamDAO;
import com.fivet.buddy.dao.TeamMemberDAO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamService {

    @Autowired
    TeamDAO teamDao;

    @Autowired
    private TeamMemberDAO teamMemberDao;

//    팀 생성
    public void insertTeam (TeamDTO teamDto, Map<String, String> param) throws Exception {
        teamDao.insert(teamDto);
        param.put("teamSeq",String.valueOf(teamDto.getTeamSeq()));
        teamMemberDao.createTeam(param);
    }

}
