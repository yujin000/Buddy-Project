package com.fivet.buddy.services;

import com.fivet.buddy.dao.NoticeBoardDAO;
import com.fivet.buddy.dto.NoticeBoardDTO;
import com.fivet.buddy.mapper.NoticeBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public void deleteNotice(int noticeSeq) {
        noticeBoardDao.deleteNotice(noticeSeq);
    }

    //공지글 보기
    public NoticeBoardDTO noticeDetail(int noticeSeq) {
        return noticeBoardDao.noticeDetail(noticeSeq);
    }

    //공지굴 수정
    public void updateNotice(NoticeBoardDTO noticeBoardDto) { noticeBoardDao.updateNotice(noticeBoardDto); }

    //공지글 페이지에 맞춰 출력
    public List<NoticeBoardDTO> selectNoticePage(Map<String, Integer> param) {
        return noticeBoardDao.selectNoticePage(param);
    }

    // 공지글 카운트
    public int totalCount() {
        return noticeBoardDao.totalCount();
    }

}
