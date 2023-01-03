package com.fivet.buddy.services;

import com.fivet.buddy.dao.TeamMemberDAO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.dto.TeamMemberListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TeamMemberService {

    @Autowired
    private TeamMemberDAO teamMemberDao;

    //회원 번호를 이용하여 해당 팀 관련 회원정보 출력.
    public TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto) {
        return teamMemberDao.selectOne(teamMemberDto);
    }

    //채팅방 참여자 목록을 출력.
    public List<TeamMemberDTO> selectChatMember(int chatRoomSeq) {
        return teamMemberDao.selectChatMember(chatRoomSeq);
    }

    //팀 관리 멤버 출력
    public List<TeamMemberListDTO> selectTeamMember(String teamSeq){
        return teamMemberDao.selectTeamMember(teamSeq);
    }

    //멤버 등급 변경
    public void updateTeamMemberGrade(TeamMemberDTO teamMemberDto){
        teamMemberDao.updateTeamMemberGrade(teamMemberDto);
    }

    //멤버 등급 변경 ( 매니저가 다른 사람한테 매니저 이양할 때 )
    public void updateTeamMemberManager(Map<String,Integer> param){
        teamMemberDao.updateTeamMemberManager(param);
    }

    //팀 관리 페이지에서 팀원 강퇴
    public void deleteTeamMember(TeamMemberDTO teamMemberDto){
        teamMemberDao.deleteTeamMember(teamMemberDto);
    }

    // 초대된 팀으로 입장
    public void enterTeam(TeamMemberDTO teamMemberDto) throws Exception{
        teamMemberDao.enterTeam(teamMemberDto);
    }

    // 부매니저인 멤버 출력 (부매니저일때도 팀 관리 들어갈 수 있게)
    public int selectSubManagerMember(int memberSeq){
        return teamMemberDao.selectSubManagerMember(memberSeq);
    }

    // 팀 회원 번호 목록 출력
    public List<Integer> selectTeamMemberSeq(int teamSeq) {
        return teamMemberDao.selectTeamMemberSeq(teamSeq);
    }

    // 회원의 팀 소속 여부 판단
    public int selectCheckMember(TeamMemberDTO teamMemberDto) {
        return teamMemberDao.selectCheckMember(teamMemberDto);
    }

    // 회원이 속한 팀 갯수 체크
    public int selectMemberTeam(int memberSeq) {
        return teamMemberDao.selectMemberTeam(memberSeq);
    }


    public int selectTeamMember(int teamSeq) {
        return teamMemberDao.selectTeamMember(teamSeq);
    }
}
