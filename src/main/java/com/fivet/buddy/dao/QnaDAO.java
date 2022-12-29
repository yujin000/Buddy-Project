package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaDTO;
import com.fivet.buddy.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaDAO {
    @Autowired
    private QnaMapper qnaMapper;

    public void insert(QnaDTO qnaDto) throws Exception{
         qnaMapper.insert(qnaDto);
    }
    public List<QnaDTO> select(int qnaWriter) throws Exception{
        return qnaMapper.select(qnaWriter);
    }

    public void delete(int qnaSeq) throws Exception{
         qnaMapper.delete(qnaSeq);
    }
}
