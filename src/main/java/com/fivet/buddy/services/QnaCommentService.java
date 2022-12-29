package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaCommentDAO;
import com.fivet.buddy.dto.QnaCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaCommentService {

    @Autowired
    private QnaCommentDAO qnaCommentDAO;
    public List<QnaCommentDTO> selectComment(int qnaSeq) throws  Exception{
        return qnaCommentDAO.selectComment(qnaSeq);
    }

    public void deleteComment(int qnaSeq,int qnaCommentSeq) throws Exception{
         qnaCommentDAO.deleteComment(qnaSeq,qnaCommentSeq);
    }
}
