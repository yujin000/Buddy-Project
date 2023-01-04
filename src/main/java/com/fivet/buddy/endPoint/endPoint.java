package com.fivet.buddy.endPoint;

import com.fivet.buddy.config.ApplicationContextProvider;
import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.services.ChatMsgService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedOutputStream;
import java.util.*;

@Service
@ServerEndpoint("/chat/{chatRoomSeq}")
public class endPoint {

    private BufferedOutputStream bos;
    @Value("${chat.save.path}")
    String chatPath;

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

        ChatMsgDTO chatMsgDto = g.fromJson(json, ChatMsgDTO.class);

        chatMsgService.insertChatMsg(chatMsgDto);
        //스크립트 어택 방지
        chatMsgDto.setChatContent(chatMsgDto.getChatContent().replace("<script>", "&lt;</script>"));

        for(Session s : sessions.get(chatMsgDto.getChatRoomSeq())) {
            s.getBasicRemote().sendText(g.toJson(chatMsgDto));
        }


    }

    @OnClose
    public void onClose(@PathParam("chatRoomSeq") int chatRoomSeq, Session s) {
        sessions.get(chatRoomSeq).remove(s);
    }
}


