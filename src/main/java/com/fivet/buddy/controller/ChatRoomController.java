package com.fivet.buddy.controller;

import com.fivet.buddy.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chatRoom/")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @RequestMapping("join")
    public String insertChatMsg(int chatRoomSeq, Model model) {
        model.addAttribute("chatRoomSeq", chatRoomSeq);
        return ("/team/teamChating");
    }

}
