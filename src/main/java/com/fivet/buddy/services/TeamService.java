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

    //팀 관리 팀 이름 출력
    public TeamDTO selectTeamOne(String teamSeq){
        return teamDao.selectTeamOne(teamSeq);
    }

    //팀 관리 팀 이름 수정
    public void updateManagementTeamName(TeamDTO teamDto){
        teamDao.updateManagementTeamName(teamDto);
    }

    //팀 삭제
    public void deleteTeam(int teamSeq){
        teamDao.deleteTeam(teamSeq);
    }

    // 팀 번호로 팀 이름 탐색
    public String selectTeamName(int teamSeq) {
        return teamDao.selectTeamName(teamSeq);
    }

    //팀 관리에서 매니저가 바뀌면 team_owner_seq 변경
    public void updateTeamOwnerSeq(Map<String,Integer> param){
        teamDao.updateTeamOwnerSeq(param);
    }

    //새로운 팀원 추가시, 팀 인원수가 1 증가
    public void updatePlusTeamCount(int teamSeq) { teamDao.updatePlusTeamCount(teamSeq); }

    //팀원 탈퇴시, 팀 인원수가 1 감소
    public void updateMinusTeamCount(int teamSeq) { teamDao.updateMinusTeamCount(teamSeq);}

    //팀원이 0명인 팀 삭제
    public void delTeamZeroCount() { teamDao.delTeamZeroCount();}

    //팀의 팀원수 추출
    public int selectTeamCount(int teamSeq) {
        return teamDao.selectTeamCount(teamSeq);
    }

    //팀이 특정 회원 1명뿐인 팀 목록을 삭제
    public void delTeamOnlyOne(int memberSeq) {
        teamDao.delTeamOnlyOne(memberSeq);
    }
}
