package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaCommentDTO;
import com.fivet.buddy.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaCommentDAO {

    @Autowired
    private QnaMapper qnaMapper;

    public List<QnaCommentDTO> selectComment(int qnaSeq) throws Exception{
        return qnaMapper.selectComment(qnaSeq);
    }

    public void deleteComment(int qnaSeq,int qnaCommentSeq) throws Exception{
         qnaMapper.deleteComment(qnaSeq,qnaCommentSeq);
    }
}
