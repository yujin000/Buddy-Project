package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatRoomDAO;
import com.fivet.buddy.dto.ChatRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomDAO chatRoomDao;

    public List<ChatRoomDTO> chatRoomList(Map<String, Integer> param) {
        return chatRoomDao.chatRoomList(param);
    }

}
