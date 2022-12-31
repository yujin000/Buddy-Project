package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaBoardDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaCommentMapper {
    List<QnaCommentDTO> selectComment(int qnaSeq);
    void deleteComment(int qnaSeq, int qnaCommentSeq);
    void insertComment(QnaCommentDTO qnaCommentDto);
    int count(int qnaSeq);
}
