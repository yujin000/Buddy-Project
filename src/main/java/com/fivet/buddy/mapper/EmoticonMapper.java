package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.EmoticonDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmoticonMapper {
    void insertEmoticon(EmoticonDTO emoticonDto);

    List<EmoticonDTO> selectEmoticon();
}
