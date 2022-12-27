package com.fivet.buddy.services;

import com.fivet.buddy.dao.NoticeDAO;
import com.fivet.buddy.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeDAO noticeDao;

    public List<NoticeDTO> select() throws Exception{
        return noticeDao.select();
    }

    public NoticeDTO selectDetail(NoticeDTO noticeDto) throws Exception{
        return noticeDao.selectDetail(noticeDto);
    }
}