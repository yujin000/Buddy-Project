package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.dto.TeamMemberListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamMemberMapper {

    void enterTeam(TeamMemberDTO teamMemberDto);
    void createTeam(Map<String, String> param);
    TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto);
    List<TeamMemberDTO> selectChatMember(int chatRoomSeq);
    List<TeamMemberListDTO> selectTeamMember(String teamSeq);
    void updateTeamMemberGrade(TeamMemberDTO teamMemberDto);
    void updateTeamMemberManager(Map<String,Integer> param);
    void deleteTeamMember(TeamMemberDTO teamMemberDto);
    int selectSubManagerMember(int memberSeq);
    List<Integer> selectTeamMemberSeq(int teamSeq);
    int selectCheckMember(TeamMemberDTO teamMemberDto);
    int countMemberTeam(int memberSeq);
}