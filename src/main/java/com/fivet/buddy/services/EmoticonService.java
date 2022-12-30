package com.fivet.buddy.services;

import com.fivet.buddy.dao.EmoticonDAO;
import com.fivet.buddy.dto.EmoticonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmoticonService {
    @Autowired
    private EmoticonDAO emoticonDAO;

    public void insertEmoticon(EmoticonDTO emoticonDto) throws Exception{
        emoticonDAO.insertEmoticon(emoticonDto);
    }
}
