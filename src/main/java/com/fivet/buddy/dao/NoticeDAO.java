package com.fivet.buddy.dao;

import com.fivet.buddy.dto.NoticeDTO;
import com.fivet.buddy.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDAO {

    @Autowired
    private NoticeMapper noticemapper;

    public List<NoticeDTO> select() throws Exception {
        return noticemapper.select();
    }
}
