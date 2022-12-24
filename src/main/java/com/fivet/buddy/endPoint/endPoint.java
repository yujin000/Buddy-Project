//package com.fivet.buddy.endPoint;
//
//import org.springframework.stereotype.Service;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.util.*;
//
//@Service
//@ServerEndpoint("/chat/{chatRoomSeq}")
//public class endPoint {
//
//    // 스프링 컨테이너에서 생성한 bean을 dependency lookup으로 찾아오기
//    private ChatMsgService service = ApplicationContextProvider.getApplicationContext().getBean(ChatMsgService.class);
//
//    private static Map<Integer, Set<Session>> sessions = Collections.synchronizedMap(new HashMap<>());
//
//    @OnOpen
//    public void onOpen(@PathParam("ChatRoomSeq") int ChatRoomSeq, Session s) {
//        System.out.println("open session : " + s.toString());
//        System.out.println("roomNum : " + ChatRoomSeq);
//
//        if(!sessions.containsKey(ChatRoomSeq)) {
//            sessions.put(ChatRoomSeq, new HashSet<>());
//        }
//        if (!sessions.get(ChatRoomSeq).contains(s)) {
//            sessions.get(ChatRoomSeq).add(s);
//            System.out.println("session open : " + s);
//        }else {
//            System.out.println("이미 연결된 session 임!!!");
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String json, Session session) throws Exception{
//        Gson g = new Gson();
//        System.out.println("receive json : " + json);
//
//        ChatMsgDTO chatMsgDto = g.fromJson(json, ChatMsgDTO.class);
//
//        for(Session s : sessions.get(chatMsgDto.getRoomNum())) {
//            System.out.println("send data : " + chatMsgDto.getMsg());
//            s.getBasicRemote().sendText(chatMsgDto.getNick() + " : " + chatMsgDto.getMsg());
//        }
//
//        service.insert(chatMsgDto);
//
//    }
//
//    @OnClose
//    public void onClose(@PathParam("ChatroomNum") int roomNum, Session s) {
//        sessions.get(ChatRoomSeq).remove(s);
//    }
//}
//
//
