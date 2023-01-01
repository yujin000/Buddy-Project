package com.fivet.buddy.controller;

import com.fivet.buddy.dao.ChatMemberDAO;
import com.fivet.buddy.dao.ChatRoomDAO;
import com.fivet.buddy.dto.*;
import com.fivet.buddy.services.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/chatRoom/")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMsgService chatMsgService;

    @Autowired
    private HttpSession session;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ChatMemberService chatMemberService;

    //채팅방 입장
    @RequestMapping("join")
    public String insertChatMsg(int chatRoomSeq, Model model) {
        model.addAttribute("chatRoomSeq",chatRoomSeq);
        model.addAttribute("chatMsgList", chatMsgService.selectChatMsg(chatRoomSeq));
        model.addAttribute("chatMemberList",teamMemberService.selectChatMember(chatRoomSeq));
        return ("/team/teamChating");
    }

    // 채팅방 목록 출력
    @ResponseBody
    @PostMapping("chatRoomList")
    public String chatRoomList() {
        Map<String, Integer> param = new HashMap<>();
        param.put("memberSeq", (int)session.getAttribute("memberSeq"));
        param.put("teamSeq", (int)session.getAttribute("teamSeq"));

        Gson g = new Gson();
        return g.toJson(chatRoomService.chatRoomList(param));
    }

    // 토픽 생성
    @PostMapping("insertTopic")
    public String insertTopic(ChatRoomDTO chatRoomDto) {
        TeamDTO teamDto = teamService.selectTeamOne(session.getAttribute("teamSeq").toString());

        //ChatRoomDTO 영역 (chatRoom테이블에 topic 생성)

        chatRoomDto = chatRoomService.insertTopic(teamDto, chatRoomDto);

        //ChatMember 영역(각 회원을 토픽에 가입)
        chatMemberService.insertTopicMember(chatRoomDto);

        return "redirect:/team/goTeamAgain";
    }

    // 일반채팅방 생성
    @ResponseBody
    @PostMapping("insertChat")
    public String insertChat(@RequestBody MakeChatDTO makeChatDto) {

        TeamDTO teamDto = teamService.selectTeamOne(session.getAttribute("teamSeq").toString());
        ChatRoomDTO chatRoomDto = new ChatRoomDTO(
                0,
                makeChatDto.getChatTitle(),
                teamDto.getTeamSeq(),
                (int)session.getAttribute("memberSeq"),
                "normal",
                makeChatDto.getMakeChat().length,
                null
                );
        chatRoomService.insertNormalChat(chatRoomDto);
        chatMemberService.insertNormalChatMember(chatRoomDto, makeChatDto.getMakeChat());

        return "redirect:/team/goTeamAgain";
    }


}
