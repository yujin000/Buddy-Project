package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaBoardDAO;
import com.fivet.buddy.dto.QnaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaBoardService {
    @Autowired
    private QnaBoardDAO qnaBoardDao;

    public void insert(QnaDTO qnaDto) throws Exception {
        qnaDto.setQnaTitle(qnaDto.getQnaTitle().replace("<", "&lt;"));
        qnaDto.setQnaContents(qnaDto.getQnaContents().replace("<", "&lt;"));
        qnaBoardDao.insert(qnaDto);
    }

    public List<QnaDTO> select(int qnaWriter) throws Exception {
        return qnaBoardDao.select(qnaWriter);
    }

    public void delete(int qnaSeq) throws Exception {
        qnaBoardDao.delete(qnaSeq);
    }
    public List<QnaDTO> selectQnaBoardAll() {
        return qnaBoardDao.selectQnaBoardAll();
    }
}
