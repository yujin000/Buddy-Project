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

    public void insert(QnaDTO qnaDto) throws Exception {
        qnaDto.setQnaTitle(qnaDto.getQnaTitle().replace("<", "&lt;"));
        qnaDto.setQnaContents(qnaDto.getQnaContents().replace("<", "&lt;"));
        qnaDao.insert(qnaDto);
    }

    public List<QnaDTO> select(int qnaWriter) throws Exception {
        return qnaDao.select(qnaWriter);
    }

    public void delete(int qnaSeq) throws Exception {
        qnaDao.delete(qnaSeq);
    }
}
