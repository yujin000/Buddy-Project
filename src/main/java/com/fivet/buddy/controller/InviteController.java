package com.fivet.buddy.controller;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.services.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/invite/")
public class InviteController {
    @Autowired
    private InviteService inviteService;

    @Autowired
    private HttpSession session;


    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 초대 테스트페이지 이동(추후 경로 변경 예정)
    @RequestMapping("test")
    public String test()throws Exception{
        return "testInvite";
    }

    // 초대확인 테스트 페이지 이동(추후 경로 변경 예정)
    @RequestMapping("testInviteConfirm")
    public String testInviteConfirm() throws Exception{
        return "testInviteConfirm";
    }

    // 초대 발송 시 invite 테이블에 초대정보 insert(초대팀 index, 초대 멤버 index(session), 수신 이메일, 초대 코드)
    @ResponseBody
    @RequestMapping("codeInsert")
    public void codeInsert(InviteDTO inviteDto) throws Exception{
        // 초대 팀seq값은 추후 session으로 대체함(test 중에서는 ajax를 통해 임의로 받아서 사용)
        //inviteDto.setInviteTeamSeq(Integer.parseInt(session.getAttribute("teamSeq").toString()));

        // 초대인
        inviteDto.setInviteSendMemSeq(Integer.parseInt(session.getAttribute("memberSeq").toString()));

        inviteService.codeInsert(inviteDto);
    }

    // invite 테이블에 코드 존재 여부 체크
    @ResponseBody
    @RequestMapping("codeCheck")
    public boolean codeCheck(String inviteCode) throws Exception{
        boolean result = inviteService.codeCheck(inviteCode);
        return result;
    }

    // 초대된 팀으로 입장
    @RequestMapping("enterTeam")
    public String enterTeam(TeamMemberDTO teamMemberDto) throws Exception{
        teamMemberDto.setMemberSeq(Integer.parseInt(session.getAttribute("memberSeq").toString()));
        // 팀 닉네임 (default : 이름)
        teamMemberDto.setTeamMemberNickname(session.getAttribute("memberName").toString());
        inviteService.enterTeam(teamMemberDto);
        return "redirect:/";
    }



    // 사용자 코드 일치 >> 사용한 초대 코드 delete
    @ResponseBody
    @RequestMapping("codeDelete")
    public int codeDelete(String inviteCode) throws Exception{
        // 해당 코드와 일치하는 팀 번호 추출
        int teamIndex = inviteService.selectTeamSeqByCode(inviteCode);
        inviteService.codeDelete(inviteCode);
        return teamIndex;
    }

}
