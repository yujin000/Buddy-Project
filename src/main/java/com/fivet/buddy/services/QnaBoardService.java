package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaBoardDAO;
import com.fivet.buddy.dto.QnaBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 관리자의 모든 문의 글 출력
    public List<QnaBoardDTO> selectQnaBoardAll() {
        return qnaBoardDao.selectQnaBoardAll();
    }
}
