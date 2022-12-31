package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaBoardMapper {
    void insert(QnaBoardDTO qnaDto);
    List<QnaBoardDTO> select(int qnaWriter);
    void delete(int qnaSeq);
    List<QnaBoardDTO> selectQnaBoardAll();


}
