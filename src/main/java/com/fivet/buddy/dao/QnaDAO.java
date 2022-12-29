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

    public int insert(QnaDTO qnaDto) throws Exception{
        return qnaMapper.insert(qnaDto);
    }
    public List<QnaDTO> select(int qnaWriter) throws Exception{
        return qnaMapper.select(qnaWriter);
    }

    public QnaDTO selectDetail(QnaDTO qnaDto) throws Exception{
        return qnaMapper.selectDetail(qnaDto);
    }
    public int delete(int qnaSeq) throws Exception{
        return qnaMapper.delete(qnaSeq);
    }

    public List<QnaDTO> selectQnaBoardAll() {
        return qnaMapper.selectQnaBoardAll();
    }
}
