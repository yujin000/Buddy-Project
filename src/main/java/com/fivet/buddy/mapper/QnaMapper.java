package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import com.fivet.buddy.dto.QnaFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
     int insert(QnaDTO qnaDto);

     int insertFile(QnaFileDTO qnaFileDto);

     List<QnaDTO> select(int qnaWriter);

     List<QnaCommentDTO> selectComment(int qnaSeq);

     QnaDTO selectDetail(QnaDTO qnaDto);

     List<QnaFileDTO> selectFile(int qnaSeq);

     int delete(int qnaSeq);

     int deleteFile(int qnaSeq);

     int deleteComment(int qnaSeq,int qnaCommentSeq);
}
