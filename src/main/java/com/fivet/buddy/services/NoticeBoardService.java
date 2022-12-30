package com.fivet.buddy.services;

import com.fivet.buddy.dao.NoticeBoardDAO;
import com.fivet.buddy.dto.NoticeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeBoardService {
    @Autowired
    private NoticeBoardDAO noticeBoardDao;

    // 공지글 목록 출력
    public List<NoticeBoardDTO> selectNotice() throws Exception{
        return noticeBoardDao.selectNotice();
    }

    // 공지글 쓰기
    public void insertNotice(NoticeBoardDTO noticeBoardDto) {
        noticeBoardDto.setNoticeContents(noticeBoardDto.getNoticeContents().replace("<script>", "&lt;script>"));
        noticeBoardDao.insertNotice(noticeBoardDto);
    }

    // 공지글 삭제
    public void deleteNotice(int noticeBoardSeq) {
        noticeBoardDao.deleteNotice(noticeBoardSeq);
    }

}
