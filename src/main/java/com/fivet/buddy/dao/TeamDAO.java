package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDAO {
    @Autowired
    private TeamMapper teamMapper;

    // 팀 생성
    public void insert(TeamDTO teamDto) {
        teamMapper.insertTeam(teamDto);
    }
    // 회원이 속한 팀 출력
    public List<TeamDTO> selectMemberTeam(int memberSeq) {
        return teamMapper.selectMemberTeam(memberSeq);
    }
}
