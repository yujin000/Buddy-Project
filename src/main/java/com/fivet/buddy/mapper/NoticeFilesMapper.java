package com.fivet.buddy.mapper;


import com.fivet.buddy.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeFilesMapper {
    List<NoticeFileDTO> selectFile(int noticeSeq);
}