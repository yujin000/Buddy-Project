package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaCommentDAO;
import com.fivet.buddy.dto.QnaBoardDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaCommentService {

    @Autowired
    private QnaCommentDAO qnaCommentDao;

    // 답글 리스트 출력
    public List<QnaCommentDTO> selectComment(int qnaSeq) throws  Exception{
        return qnaCommentDao.selectComment(qnaSeq);
    }

    // 답글 삭제 (미사용)
    public void deleteComment(int qnaSeq,int qnaCommentSeq) throws Exception{
         qnaCommentDao.deleteComment(qnaSeq,qnaCommentSeq);
    }

    // 답글 입력
    public void insertComment(QnaCommentDTO qnaCommentDto) {
        qnaCommentDao.insertComment(qnaCommentDto);
    }

    public int count(int qnaSeq) {
        return qnaCommentDao.count(qnaSeq);
    }
}
