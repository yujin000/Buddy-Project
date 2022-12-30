package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.EmoticonDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmoticonMapper {
    void insertEmoticon(EmoticonDTO emoticonDto);
}
