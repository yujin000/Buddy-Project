package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    void insertTeam(TeamDTO dto);
    List<TeamDTO> selectMemberTeam(int memberSeq);

    List<TeamMemberDTO> managementTeamSelectTeamMember(String teamSeq);

    String managementTeamSelectTeam(String teamSeq);
}
