package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDAO {
    @Autowired
    private TeamMapper teamMapper;

    // 팀 생성
    public void insertTeam(TeamDTO teamDto) {
        teamMapper.insertTeam(teamDto);
    }
    // 회원이 속한 팀 출력
    public List<TeamDTO> selectMemberTeam(int memberSeq) {
        return teamMapper.selectMemberTeam(memberSeq);
    }

    //팀 관리 멤버 출력
    public List<TeamMemberDTO> managementTeamSelectTeamMember(String teamSeq){
        return teamMapper.managementTeamSelectTeamMember(teamSeq);
    }

    //팀 관리 팀 이름 출력
    public TeamDTO managementTeamSelectTeam(String teamSeq){
        return teamMapper.managementTeamSelectTeam(teamSeq);
    }

    //팀 관리 팀 이름 수정
    public void managementUpdateTeamName(TeamDTO teamDto){
        teamMapper.managementUpdateTeamName(teamDto);
    }

    //팀 삭제
    public void deleteTeam(int teamSeq){
        System.out.println("dao");
        teamMapper.deleteTeam(teamSeq);
    }
}
