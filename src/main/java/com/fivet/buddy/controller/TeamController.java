package com.fivet.buddy.controller;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/team/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    HttpSession session;

    //팀 생성 페이지로 이동
    @RequestMapping("add")
    public String add() {
        return "team/teamAdd";
    }

    //팀 생성
    @RequestMapping("create")
    public String create(TeamDTO teamDto) throws Exception {
        teamDto.setTeamOwnerSeq((Integer) session.getAttribute("memberSeq"));
        Map<String, String> param = new HashMap<>();
        param.put("memberSeq", session.getAttribute("memberSeq").toString());
        // session값인 이름만 닉네임에 담아 service에 전송.
        param.put("teamMemberNickname", session.getAttribute("memberName").toString());
        teamService.insertTeam(teamDto, param);

        return "redirect:/";
    }

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return "error";
    }
}
