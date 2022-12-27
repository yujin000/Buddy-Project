package com.fivet.buddy.dao;


import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeFileDAO {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<NoticeFileDTO> selectFile() throws Exception{
        return noticeMapper.selectFile();
    }
    public NoticeFileDTO selectFileDetail(NoticeFileDTO noticeFileDto) throws Exception{
        return noticeMapper.selectFileDetail(noticeFileDto);
    }
}
