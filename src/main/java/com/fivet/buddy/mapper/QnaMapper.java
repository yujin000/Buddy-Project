package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaDTO;
import com.fivet.buddy.dto.QnaCommentDTO;
import com.fivet.buddy.dto.QnaFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    void insert(QnaDTO qnaDto);

    void insertFile(QnaFileDTO qnaFileDto);

    List<QnaDTO> select(int qnaWriter);

    List<QnaCommentDTO> selectComment(int qnaSeq);

    List<QnaFileDTO> selectFile(int qnaSeq);

    void delete(int qnaSeq);

    void deleteFile(int qnaSeq);

    void deleteComment(int qnaSeq, int qnaCommentSeq);

    List<QnaDTO> selectQnaBoardAll();
}
