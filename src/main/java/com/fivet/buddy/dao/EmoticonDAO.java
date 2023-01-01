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

    public void insertEmoticon(EmoticonDTO emoticonDto) throws Exception{
        emoticonMapper.insertEmoticon(emoticonDto);
    }

    public List<EmoticonDTO> selectEmoticon() throws Exception{
        return emoticonMapper.selectEmoticon();
    }
}