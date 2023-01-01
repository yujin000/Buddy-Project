package com.fivet.buddy.controller;

import com.fivet.buddy.dto.InviteDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/invite/")
public class InviteController {
    @Autowired
    private InviteService inviteService;

    @Autowired
    private HttpSession session;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MemberService memberService;


    // ExceptionHandler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }

    // 초대 페이지 이동
    @RequestMapping("sendInviteMail")
    public String sendInviteMail(Model model)throws Exception{
        model.addAttribute("memberName",memberService.getOwnerName((Integer)session.getAttribute("memberSeq")));
        model.addAttribute("teamSeq",session.getAttribute("teamSeq"));
        model.addAttribute("teamName",session.getAttribute("teamName"));
        return "invite/sendInviteMail";
    }

    // 초대 코드 입력페이지 이동
    @RequestMapping("goEnterTeam")
    public String testInviteConfirm() throws Exception{
        return "invite/enterTeam";
    }

    // 초대 발송 시 invite 테이블에 초대정보 insert(초대팀 index, 초대 멤버 index(session), 수신 이메일, 초대 코드)
    @ResponseBody
    @RequestMapping("codeInsert")
    public void codeInsert(InviteDTO inviteDto) throws Exception{
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
        teamMemberDto.setMemberId(memberService.getMemberId((Integer)session.getAttribute("memberSeq")));
        teamMemberService.enterTeam(teamMemberDto);
        teamService.updatePlusTeamCount(teamMemberDto.getTeamSeq());

        //기본 채팅방에 추가 및 나와의 채팅 생성을 위한 Map 구성
        Map<String, String> param = new HashMap<>();
        param.put("memberSeq", session.getAttribute("memberSeq").toString());
        chatRoomService.addBasicAndSelfChat(teamMemberDto, param);

        return "redirect:/member/loginIndex";
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
