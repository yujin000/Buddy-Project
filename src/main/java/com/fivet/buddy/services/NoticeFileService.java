package com.fivet.buddy.services;

import com.fivet.buddy.dao.NoticeFilesDAO;
import com.fivet.buddy.dto.NoticeFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeFileService {

    @Autowired
    private NoticeFilesDAO noticeFileDao;

    public List<NoticeFileDTO> selectFile(int noticeSeq) throws Exception {
        return noticeFileDao.selectFile(noticeSeq);
    }

}
