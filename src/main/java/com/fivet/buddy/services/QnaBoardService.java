package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaBoardDAO;
import com.fivet.buddy.dto.QnaBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QnaBoardService {
    @Autowired
    private QnaBoardDAO qnaBoardDao;

    // 1:1문의 글 입력
    public void insert(QnaBoardDTO qnaDto) throws Exception {
        qnaDto.setQnaTitle(qnaDto.getQnaTitle().replace("<", "&lt;"));
        qnaDto.setQnaContents(qnaDto.getQnaContents().replace("<", "&lt;"));
        qnaBoardDao.insert(qnaDto);
    }

    // 회원의 1:1문의 글 목록
    public List<QnaBoardDTO> select(int qnaWriter) throws Exception {
        return qnaBoardDao.select(qnaWriter);
    }

    // 1:1문의 글 삭제
    public void delete(int qnaSeq) throws Exception {
        qnaBoardDao.delete(qnaSeq);
    }

    // 관리자에게 모든 문의 글 출력
    public List<QnaBoardDTO> selectQnaBoardAll() {
        return qnaBoardDao.selectQnaBoardAll();
    }

    // 문의글 페이지에 따라 출력
    public List<QnaBoardDTO> selectQnaBoardPage(Map<String, Integer> param) {
        return qnaBoardDao.selectQnaBoardPage(param);
    }

    // 문의글 전체수 출력
    public int totalCount() {
        return qnaBoardDao.totalCount();
    }
}
