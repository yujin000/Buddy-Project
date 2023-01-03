package com.fivet.buddy.dao;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.dto.TeamMemberListDTO;
import com.fivet.buddy.mapper.TeamMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    //회원 번호를 이용하여 해당 팀 관련 회원정보 출력.
    public TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto) {
        return teamMemberMapper.selectOne(teamMemberDto);
    }

    // 기본채팅방 참여자 목록을 출력.
    public List<TeamMemberDTO> selectChatMember(int chatRooMSeq) {
        return teamMemberMapper.selectChatMember(chatRooMSeq);
    }

    //팀 관리 멤버 출력
    public List<TeamMemberListDTO> selectTeamMember(String teamSeq){
        return teamMemberMapper.selectTeamMember(teamSeq);
    }

    //멤버 등급 변경
    public void updateTeamMemberGrade(TeamMemberDTO teamMemberDto){
        teamMemberMapper.updateTeamMemberGrade(teamMemberDto);
    }

    //멤버 등급 변경 ( 매니저가 다른 사람한테 매니저 이양할 때 )
    public void updateTeamMemberManager(Map<String,Integer> param){
        teamMemberMapper.updateTeamMemberManager(param);
    }

    //팀 관리 페이지에서 팀원 강퇴
    public void deleteTeamMember(TeamMemberDTO teamMemberDto){
        teamMemberMapper.deleteTeamMember(teamMemberDto);
    }

    //부매니저인 멤버 출력 (부매니저일때도 팀 관리 들어갈 수 있게)
    public int selectSubManagerMember(int memberSeq){
        return teamMemberMapper.selectSubManagerMember(memberSeq);
    }

    // 팀 회원번호 리스트 출력
    public List<Integer> selectTeamMemberSeq(int teamSeq) {
        return teamMemberMapper.selectTeamMemberSeq(teamSeq);
    }

    // 팀에 회원이 소속되있는지 체크
    public int selectCheckMember (TeamMemberDTO teamMemberDto) {
        return teamMemberMapper.selectCheckMember(teamMemberDto);
    }

    // 회원이 속한 팀 숫자 체크
    public int selectMemberTeam (int memberSeq) {
        return teamMemberMapper.countMemberTeam(memberSeq);
    }

    public int selectTeamMember(int TeamSeq) {
        return teamMemberMapper.countTeamMember(TeamSeq);
    }

    //부매니저 수 체크
    public int subManagerCount(int teamSeq){
        return teamMemberMapper.subManagerCount(teamSeq);
    }
}
