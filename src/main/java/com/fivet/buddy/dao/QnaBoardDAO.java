package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaDTO;
import com.fivet.buddy.mapper.QnaBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaBoardDAO {
    @Autowired
    private QnaBoardMapper qnaBoardMapper;

    public void insert(QnaDTO qnaDto) throws Exception{
        qnaBoardMapper.insert(qnaDto);
    }
    public List<QnaDTO> select(int qnaWriter) throws Exception{
        return qnaBoardMapper.select(qnaWriter);
    }

    public void delete(int qnaSeq) throws Exception{
        qnaBoardMapper.delete(qnaSeq);
    }

    public List<QnaDTO> selectQnaBoardAll() {
        return qnaBoardMapper.selectQnaBoardAll();
    }
}
