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

    public List<QnaCommentDTO> selectComment(QnaCommentDTO qnaCommentDto) throws Exception{
        return qnaMapper.selectComment(qnaCommentDto);
    }

    public int deleteComment(int qnaSeq,int qnaCommentSeq) throws Exception{
        return qnaMapper.deleteComment(qnaSeq,qnaCommentSeq);
    }
}
