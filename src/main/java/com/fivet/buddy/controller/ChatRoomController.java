package com.fivet.buddy.controller;

import com.fivet.buddy.services.ChatMsgService;
import com.fivet.buddy.services.ChatRoomService;
import com.fivet.buddy.services.TeamMemberService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping("chatRoomList")
    public String chatRoomList() {
        Map<String, Integer> param = new HashMap<>();
        param.put("memberSeq", (int)session.getAttribute("memberSeq"));
        param.put("teamSeq", (int)session.getAttribute("teamSeq"));

        Gson g = new Gson();
        return g.toJson(chatRoomService.chatRoomList(param));
    }

}
