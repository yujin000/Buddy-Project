package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaBoardMapper {
    void insert(QnaDTO qnaDto);
    List<QnaDTO> select(int qnaWriter);
    void delete(int qnaSeq);
    List<QnaDTO> selectQnaBoardAll();


}
