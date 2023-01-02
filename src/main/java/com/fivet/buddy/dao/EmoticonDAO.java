package com.fivet.buddy.dao;

import com.fivet.buddy.dto.EmoticonDTO;
import com.fivet.buddy.mapper.EmoticonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmoticonDAO {

    @Autowired
    private EmoticonMapper emoticonMapper;

    //이모티콘 추가
    public void insertEmoticon(EmoticonDTO emoticonDto) throws Exception{
        emoticonMapper.insertEmoticon(emoticonDto);
    }

    //이모티콘 출력
    public List<EmoticonDTO> selectEmoticon() throws Exception{
        return emoticonMapper.selectEmoticon();
    }

    //이모티콘 삭제
    public void deleteEmoticon(int emoticonSeq){
        emoticonMapper.deleteEmoticon(emoticonSeq);
    }
}
