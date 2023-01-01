package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaBoardDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import com.fivet.buddy.mapper.QnaCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaCommentDAO {

    @Autowired
    private QnaCommentMapper qnaCommentMapper;

    // 댓글 목록 출력
    public List<QnaCommentDTO> selectComment(int qnaSeq) throws Exception{
        return qnaCommentMapper.selectComment(qnaSeq);
    }

    // 댓글 삭제
    public void deleteComment(int qnaSeq,int qnaCommentSeq) throws Exception{
         qnaCommentMapper.deleteComment(qnaSeq,qnaCommentSeq);
    }

    // 댓글 입력
    public void insertComment(QnaCommentDTO qnaCommentDto) {
        qnaCommentMapper.insertComment(qnaCommentDto);
    }

    // 댓글 유무 확인
    public int count(int qnaSeq) {
        return qnaCommentMapper.count(qnaSeq);
    }

}
