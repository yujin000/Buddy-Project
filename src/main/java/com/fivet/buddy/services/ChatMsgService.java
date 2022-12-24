package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatMsgDAO;
import com.fivet.buddy.dto.ChatMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMsgService {

    @Autowired
    private ChatMsgDAO chatMsgDao;
}
