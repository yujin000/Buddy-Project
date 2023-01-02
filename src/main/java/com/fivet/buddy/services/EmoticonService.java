package com.fivet.buddy.services;

import com.fivet.buddy.dao.EmoticonDAO;
import com.fivet.buddy.dto.EmoticonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmoticonService {
    @Autowired
    private EmoticonDAO emoticonDAO;

    //이모티콘 관리자 페이지로
    public void insertEmoticon(EmoticonDTO emoticonDto) throws Exception{
        emoticonDAO.insertEmoticon(emoticonDto);
    }

    //이모티콘 추가
    public List<EmoticonDTO> selectEmoticon() throws Exception{
        return emoticonDAO.selectEmoticon();
    }

    //이모티콘 삭제
    public void deleteEmoticon(int emoticonSeq){
        emoticonDAO.deleteEmoticon(emoticonSeq);
    }
}
