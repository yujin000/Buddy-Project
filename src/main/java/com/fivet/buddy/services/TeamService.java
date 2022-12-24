package com.fivet.buddy.services;

import com.fivet.buddy.dao.*;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
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

    @Autowired
    private MemberDAO memberDao;

    //팀 생성 및 기본 채팅방 생성
    public void insertTeam (TeamDTO teamDto, Map<String, String> param) throws Exception {
        teamDao.insertTeam(teamDto);
        param.put("teamSeq",String.valueOf(teamDto.getTeamSeq()));
        param.put("teamOwnerSeq", param.get("memberSeq"));
        param.put("teamName", teamDto.getTeamName());
        teamMemberDao.createTeam(param);
        chatRoomDao.createTeam(param);
        chatMemberDao.createTeam(param);
    }

    //회원 팀 출력
    public List<TeamDTO> selectMemberTeam(int memberSeq) {
        return teamDao.selectMemberTeam(memberSeq);
    }

    //팀 관리 멤버 출력
    public List<TeamMemberDTO> managementTeamSelectTeamMember(String teamSeq){
        return teamDao.managementTeamSelectTeamMember(teamSeq);
    }

    //팀 관리 팀 이름 출력
    public TeamDTO managementTeamSelectTeam(String teamSeq){
        return teamDao.managementTeamSelectTeam(teamSeq);
    }

    //팀 관리 팀 이름 수정
    public void managementUpdateTeamName(TeamDTO teamDto){
        teamDao.managementUpdateTeamName(teamDto);
    }

    //팀 삭제
    public void deleteTeam(int teamSeq){
        System.out.println("서비스");
        teamDao.deleteTeam(teamSeq);
    }
}
