package com.fivet.buddy.services;

import com.fivet.buddy.dao.ChatFilesDAO;
import com.fivet.buddy.dto.ChatFilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatFilesService {

    @Autowired
    private ChatFilesDAO chatFilesDao;

    public void insertFiles(ChatFilesDTO chatFilesDto) throws Exception {
        chatFilesDao.insertFiles(chatFilesDto);
    }
}
