package com.fivet.buddy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivet.buddy.dto.ChatRoomDTO;
import com.fivet.buddy.services.ChatRoomService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chatRoom/")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private HttpSession session;

    @RequestMapping("join")
    public String insertChatMsg(int chatRoomSeq, Model model) {
        model.addAttribute("chatRoomSeq", chatRoomSeq);
        return ("/team/teamChating");
    }

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
