package com.fivet.buddy.services;

import com.fivet.buddy.dao.InviteDAO;
import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    private InviteDAO inviteDao;

    // invite 테이블에 코드 존재 여부 체크
    public boolean codeCheck(String inviteCode) throws Exception{
        return inviteDao.codeCheck(inviteCode);
    }

    // 초대 발송 시 invite 테이블에 초대정보 insert
    public void codeInsert(InviteDTO inviteDto) throws Exception{
        inviteDao.codeInsert(inviteDto);
    }

    // 사용자 코드 일치 >> 사용한 초대 코드 delete
    public void codeDelete(String inviteCode) throws Exception{
        inviteDao.codeDelete(inviteCode);
    }

    // 초대된 팀으로 입장
    public void enterTeam(TeamDTO teamDto) throws Exception{
        inviteDao.enterTeam(teamDto);
    }

    // 코드에 맞는 팀 seq 검색
    public int selectTeamSeqByCode(String inviteCode) throws Exception{
        return inviteDao.selectTeamSeqByCode(inviteCode);
    }
}
