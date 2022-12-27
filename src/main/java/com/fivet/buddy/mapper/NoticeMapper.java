package com.fivet.buddy.mapper;


import com.fivet.buddy.dto.NoticeDTO;
import com.fivet.buddy.dto.NoticeFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> select();
    NoticeDTO selectDetail(NoticeDTO dto);

    List<NoticeFileDTO> selectFile();

    NoticeFileDTO selectFileDetail(NoticeFileDTO noticeFileDto);
}
