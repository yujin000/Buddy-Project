package com.fivet.buddy.dao;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.mapper.InviteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDAO {
    @Autowired
    private InviteMapper inviteMapper;

    // invite 테이블에 코드 존재 여부 체크
    public boolean codeCheck(String inviteCode) throws Exception{
        return inviteMapper.codeCheck(inviteCode);
    }

    // 초대 발송 시 invite 테이블에 초대정보 insert
    public void codeInsert(InviteDTO inviteDto) throws Exception{
        inviteMapper.codeInsert(inviteDto);
    }

    // 사용자 코드 일치 >> 사용한 초대 코드 delete
    public void codeDelete(String inviteCode) throws Exception{
        inviteMapper.codeDelete(inviteCode);
    }

    // 초대된 팀으로 입장
    public void enterTeam(TeamMemberDTO teamMemberDto) throws Exception{
        inviteMapper.enterTeam(teamMemberDto);
    }

    // 코드에 맞는 팀 seq 검색
    public int selectTeamSeqByCode(String inviteCode) throws Exception{
        return inviteMapper.selectTeamSeqByCode(inviteCode);
    }
}
