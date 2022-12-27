package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaDAO;
import com.fivet.buddy.dto.QnaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {
    @Autowired
    private QnaDAO qnaDao;

    public int insert(QnaDTO qnaDto) throws Exception {
        return qnaDao.insert(qnaDto);
    }
    public List<QnaDTO> select() throws  Exception{
        return qnaDao.select();
    }

    public QnaDTO selectDetail(QnaDTO qnaDto) throws  Exception{
        return qnaDao.selectDetail(qnaDto);
    }
    public int delete(int qnaSeq) throws Exception {
        return qnaDao.delete(qnaSeq);
    }
}
