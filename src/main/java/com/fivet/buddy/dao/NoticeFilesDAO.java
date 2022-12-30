package com.fivet.buddy.dao;


import com.fivet.buddy.dto.NoticeFileDTO;
import com.fivet.buddy.mapper.NoticeFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeFilesDAO {

    @Autowired
    private NoticeFilesMapper noticeFilesMapper;

    public List<NoticeFileDTO> selectFile(int noticeSeq) throws Exception{
        return noticeFilesMapper.selectFile(noticeSeq);
    }
}
