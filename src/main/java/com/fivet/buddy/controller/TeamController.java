package com.fivet.buddy.controller;

import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.dto.TeamMemberDTO;
import com.fivet.buddy.services.ChatRoomService;
import com.fivet.buddy.services.TeamMemberService;
import com.fivet.buddy.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/team/")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private HttpSession session;

    @Autowired
    private TeamMemberService teamMemberService;

    //팀 생성 페이지로 이동
    @RequestMapping("add")
    public String add() {
        return "team/teamAdd";
    }

    //팀 생성
    @PostMapping("createTeam")
    public String create(TeamDTO teamDto, ChatRoomDTO chatRoomDto) throws Exception {
        teamDto.setTeamOwnerSeq((Integer) session.getAttribute("memberSeq"));
        Map<String, String> param = new HashMap<>();
        param.put("memberSeq", session.getAttribute("memberSeq").toString());
        // session값인 이름만 닉네임에 담아 service에 전송.
        param.put("teamMemberNickname", session.getAttribute("memberName").toString());
        teamService.insertTeam(teamDto, param);

        return "redirect:/member/loginIndex";
    }

    //팀 이동
    @PostMapping("goTeam")
    public String goTeam(TeamMemberDTO teamMemberDto, Model model) {

        teamMemberDto.setMemberSeq((int)session.getAttribute("memberSeq"));
        // 회원 번호를 이용하여 팀 DTO값을 불러옴.
        teamMemberDto = teamMemberService.selectOne(teamMemberDto);
        // 팀 번호 session 부여
        session.setAttribute("teamSeq", teamMemberDto.getTeamSeq());
        // 회원의 팀내 닉네임 session 부여
        session.setAttribute("teamMemberNickname", teamMemberDto.getTeamMemberNickname());
        // 팀 이름 session 부여
        session.setAttribute("teamName", teamService.selectTeamName(teamMemberDto.getTeamSeq()));
        //teamSeq와 memberSeq를 담아 서비스 및 sql문에 전달할 Map
        Map<String, Integer> param = new HashMap<>();
        param.put("teamSeq", teamMemberDto.getTeamSeq());
        param.put("memberSeq", teamMemberDto.getMemberSeq());
        // 팀 입장시, 해당 팀 해당 회원의 채팅방 목록 출력
        List<ChatRoomDTO> chatRoomList = chatRoomService.chatRoomList(param);
        model.addAttribute("chatRoomList", chatRoomList);
        return "team/team";
    }

    //팀 관리 이동
    @RequestMapping("goTeamSetting")
    public String goTeamSetting(int teamSeq, Model model) throws Exception{
        List<TeamMemberDTO> teamMemberDtoList =  teamService.selectTeamMemberOne(String.valueOf(teamSeq));
        TeamDTO teamDto =teamService.selectTeamOne(String.valueOf(teamSeq));
        model.addAttribute("teamMemberDtoList", teamMemberDtoList);
        model.addAttribute("teamDto",teamDto);
        model.addAttribute("sessionMemberSeq",session.getAttribute("memberSeq"));
        return "team/teamSetting";
    }

    //팀 관리 팀 이름 수정
    @RequestMapping("updateTeamName")
    public void updateManagementTeamName(TeamDTO teamDto) throws Exception{
        teamService.updateManagementTeamName(teamDto);
    }

    //팀 삭제
    @RequestMapping("deleteTeam")
    public String deleteTeam(int teamSeq){
        teamService.deleteTeam(teamSeq);
        return "redirect:/member/loginIndex";
    }

    //멤버 등급 변경
    @ResponseBody
    @PostMapping("updateTeamMemberGrade")
    public void updateTeamMemberGrade(TeamMemberDTO teamMemberDto) {
        teamMemberService.updateTeamMemberGrade(teamMemberDto);

        //만약에 매니저가 다른 팀원에게 매니저를 이양한다면
        if(teamMemberDto.getGrade().equals("manager")){
            // 이전 dto값은 변경되는 회원의 memberSeq값이 들어있음
            Map<String, Integer> param = new HashMap<>();
            param.put("memberSeq",teamMemberDto.getMemberSeq());
            param.put("teamSeq", teamMemberDto.getTeamSeq());
            param.put("newManagerSeq", teamMemberDto.getMemberSeq());
            param.put("manageSeq", (Integer) session.getAttribute("memberSeq"));
            //원래 매니저를 팀원으로 등급 변경
            teamMemberService.updateTeamMemberManager(param);
            //team 테이블의 team_owner_seq 컬럼 값을 새로 변경된 매니저 member_seq로 변경
            teamService.updateTeamOwnerSeq(param);
        }
    }

    //팀 관리 페이지에서 팀원 강퇴
    @ResponseBody
    @RequestMapping("deleteTeamMember")
    public void deleteTeamMember(TeamMemberDTO teamMemberDto){
        teamMemberService.deleteTeamMember(teamMemberDto);
    }
    @RequestMapping("teamOut")

    public String teamOut() {
        // 팀 관련 session값 제거.
        session.removeAttribute("teamSeq");
        session.removeAttribute("teamMemberNickname");
        session.removeAttribute("teamName");
        return "redirect:/member/loginIndex";
    }

    // Exception Handler
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return "error";
    }
}
