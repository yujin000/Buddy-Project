package com.fivet.buddy.services;

import com.fivet.buddy.dao.QnaFileDAO;
import com.fivet.buddy.dto.QnaFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaFileService {

    @Autowired
    private QnaFileDAO qnaFileDao;

    public void insertFile(QnaFileDTO qnaFileDto) throws Exception {
         qnaFileDao.insertFile(qnaFileDto);
    }

    public List<QnaFileDTO> selectFile(int qnaSeq) throws Exception{
        return qnaFileDao.selectFile(qnaSeq);
    }
    public void deleteFile(int qnaSeq) throws Exception{
         qnaFileDao.deleteFile(qnaSeq);
    }
}
