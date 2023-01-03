package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeamMapper {
    void insertTeam(TeamDTO dto);
    List<TeamDTO> selectMemberTeam(int memberSeq);

    TeamDTO selectTeamOne(String teamSeq);

    void updateManagementTeamName(TeamDTO teamDto);

    void deleteTeam(int teamSeq);

    String selectTeamName(int teamSeq);

    void updateTeamOwnerSeq(Map<String,Integer> param);
    void updatePlusTeamCount(int teamSeq);
    void updateMinusTeamCount(int teamSeq);
    void delTeamZeroCount();
    int selectTeamCount(int teamSeq);
    void delTeamOnlyOne(int memberSeq);
}
