package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.dto.TeamDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InviteMapper {

    boolean codeCheck(String inviteCode);

    void codeInsert(InviteDTO inviteDto);

    void codeDelete(String inviteCode);

    void enterTeam(TeamDTO teamDto);

    int selectTeamSeqByCode(String inviteCode);
}
