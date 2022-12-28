package com.fivet.buddy.services;

import com.fivet.buddy.dao.*;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //param에 session값으로 memberSeq, teamMemberNickname 들어옴.
        teamDao.insertTeam(teamDto);
        param.put("teamSeq",String.valueOf(teamDto.getTeamSeq()));
        param.put("teamName", teamDto.getTeamName());
        param.put("teamOwnerSeq", String.valueOf(teamDto.getTeamOwnerSeq()));
        // teamMemberDao.createTeam(param) 에서 param에 memberId(이메일) 입력됨.
        teamMemberDao.createTeam(param);
        // 기본채팅방 생성
        chatRoomDao.createTeam(param);
        chatMemberDao.insertChatMember(param);

        // 나와의대화 생성
        chatRoomDao.insertSelfChat(param);
        chatMemberDao.insertChatMember(param);
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
        teamDao.deleteTeam(teamSeq);
    }

    // 팀 번호로 팀 이름 탐색
    public String selectTeamName(int teamSeq) {
        return teamDao.selectTeamName(teamSeq);
    }
}
