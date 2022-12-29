package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public List<TeamMemberDTO> selectTeamMemberOne(String teamSeq){
        return teamMapper.selectTeamMemberOne(teamSeq);
    }

    //팀 관리 팀 이름 출력
    public TeamDTO selectTeamOne(String teamSeq){
        return teamMapper.selectTeamOne(teamSeq);
    }

    //팀 관리 팀 이름 수정
    public void updateManagementTeamName(TeamDTO teamDto){
        teamMapper.updateManagementTeamName(teamDto);
    }

    //팀 삭제
    public void deleteTeam(int teamSeq){
        teamMapper.deleteTeam(teamSeq);
    }

    // 팀 이름으로 회원 출력
    public String selectTeamName(int teamSeq) {
        return teamMapper.selectTeamName(teamSeq);
    }

    //팀 관리에서 매니저가 바뀌면 team_owner_seq 변경
    public void updateTeamOwnerSeq(Map<String,Integer> param){
        teamMapper.updateTeamOwnerSeq(param);
    }

    //새로운 팀원 추가시, 팀 인원을 +1 증가
    public void updatePlusTeamCount(int teamSeq) { teamMapper.updatePlusTeamCount(teamSeq);}
}
