package com.fivet.buddy.services;

import com.fivet.buddy.dao.*;
import com.fivet.buddy.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        teamDao.insert(teamDto);
        param.put("teamSeq",String.valueOf(teamDto.getTeamSeq()));
        param.put("teamOwnerSeq", param.get("memberSeq"));
        param.put("teamName", teamDto.getTeamName());
        param.put("memberId", memberDao.selectId(Integer.parseInt(param.get("teamOwnerSeq"))));
        teamMemberDao.createTeam(param);
        chatRoomDao.createTeam(param);
        chatMemberDao.createTeam(param);
    }

    //회원 팀 출력
    public List<TeamDTO> selectMemberTeam(int memberSeq) {
        return teamDao.selectMemberTeam(memberSeq);
    }
}
