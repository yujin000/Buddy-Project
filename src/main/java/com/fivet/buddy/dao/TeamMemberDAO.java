package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.TeamMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TeamMemberDAO {

    @Autowired
    TeamMemberMapper teamMemberMapper;

    // 생성한 팀으로 입장
    public void createTeam(Map<String, String> param) throws Exception{
        teamMemberMapper.createTeam(param);
    }

    // 초대된 팀으로 입장
    public void enterTeam(TeamMemberDTO teamMemberDto) throws Exception{
        teamMemberMapper.enterTeam(teamMemberDto);
    }

    public TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto) {
        return teamMemberMapper.selectOne(teamMemberDto);
    }

}
