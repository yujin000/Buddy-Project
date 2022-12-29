package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaFileDTO;
import com.fivet.buddy.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaFileDAO {
    @Autowired
    private QnaMapper qnaMapper;

    public void insertFile(QnaFileDTO qnaFileDto) throws Exception {
        qnaMapper.insertFile(qnaFileDto);
    }

    public List<QnaFileDTO> selectFile(int qnaSeq) throws Exception {
        return qnaMapper.selectFile(qnaSeq);
    }

    public void deleteFile(int qnaSeq) throws Exception {
        qnaMapper.deleteFile(qnaSeq);
    }
}
