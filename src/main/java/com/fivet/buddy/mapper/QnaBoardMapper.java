package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaBoardMapper {
    void insert(QnaBoardDTO qnaDto);
    List<QnaBoardDTO> select(int qnaWriter);
    void delete(int qnaSeq);
    List<QnaBoardDTO> selectQnaBoardAll();
    List<QnaBoardDTO> selectQnaBoardPage(Map<String, Integer> param);
    int totalCount();
}
