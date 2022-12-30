package com.fivet.buddy.mapper;


import com.fivet.buddy.dto.NoticeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeBoardMapper {
    List<NoticeBoardDTO> selectNotice();

    void insertNotice(NoticeBoardDTO noticeBoardDto);
}
