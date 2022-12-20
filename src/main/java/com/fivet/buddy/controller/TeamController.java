package com.fivet.buddy.controller;

import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String create(TeamDTO teamDto) {
        teamDto.setTeamSeq((Integer) session.getAttribute("memberSeq"));
        teamService.insert(teamDto);
        return "redirect:/";
    }
}
