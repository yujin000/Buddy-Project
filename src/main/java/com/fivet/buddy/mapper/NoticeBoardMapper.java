package com.fivet.buddy.mapper;


import com.fivet.buddy.dto.NoticeBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeBoardMapper {
    List<NoticeBoardDTO> selectNotice();
    void insertNotice(NoticeBoardDTO noticeBoardDto);
    void deleteNotice(int noticeBoardSeq);
    NoticeBoardDTO noticeDetail(int noticeSeq);
    void updateNotice(NoticeBoardDTO noticeBoardDto);
    List<NoticeBoardDTO> selectNoticePage(Map<String, Integer> param);
    int totalCount();

}
