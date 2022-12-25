package com.fivet.buddy.endPoint;

import com.fivet.buddy.config.ApplicationContextProvider;
import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.services.ChatMsgService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

@Service
@ServerEndpoint("/chat/{chatRoomSeq}")
public class endPoint {

    // 스프링 컨테이너에서 생성한 bean을 dependency lookup으로 찾아오기
    private ChatMsgService chatMsgService = ApplicationContextProvider.getApplicationContext().getBean(ChatMsgService.class);

    private static Map<Integer, Set<Session>> sessions = Collections.synchronizedMap(new HashMap<>());

    @OnOpen
    public void onOpen(@PathParam("chatRoomSeq") int ChatRoomSeq, Session s) {

        if(!sessions.containsKey(ChatRoomSeq)) {
            sessions.put(ChatRoomSeq, new HashSet<>());
        }
        if (!sessions.get(ChatRoomSeq).contains(s)) {
            sessions.get(ChatRoomSeq).add(s);
        }
    }

    @OnMessage
    public void onMessage(String json, Session session) throws Exception{
        Gson g = new Gson();
        //System.out.println("receive json : " + json);

        ChatMsgDTO chatMsgDto = g.fromJson(json, ChatMsgDTO.class);

        for(Session s : sessions.get(chatMsgDto.getChatRoomSeq())) {
            //System.out.println("send data : " + chatMsgDto.getChatContent());
            s.getBasicRemote().sendText(chatMsgDto.getTeamMemberNickname() + " <br> " + chatMsgDto.getChatContent());
        }

        chatMsgService.insertChatMsg(chatMsgDto);

    }

    @OnClose
    public void onClose(@PathParam("chatRoomSeq") int chatRoomSeq, Session s) {
        sessions.get(chatRoomSeq).remove(s);
    }
}


