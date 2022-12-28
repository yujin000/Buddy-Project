package com.fivet.buddy.services;

import com.fivet.buddy.dao.NoticeFileDAO;
import com.fivet.buddy.dto.NoticeFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeFileService {

    @Autowired
    private NoticeFileDAO noticeFileDao;

    public List<NoticeFileDTO> selectFile(int noticeSeq) throws Exception {
        return noticeFileDao.selectFile(noticeSeq);
    }

    public NoticeFileDTO selectFileDetail(NoticeFileDTO noticeFileDto) throws Exception {
        return noticeFileDao.selectFileDetail(noticeFileDto);
    }
}
