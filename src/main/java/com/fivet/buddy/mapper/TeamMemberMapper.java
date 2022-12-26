package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.TeamMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TeamMemberMapper {

    void enterTeam(TeamMemberDTO teamMemberDto);
    void createTeam(Map<String, String> param);
    TeamMemberDTO selectOne(TeamMemberDTO teamMemberDto);
    TeamMemberDTO selectChatMember(int chatRoomSeq);

}