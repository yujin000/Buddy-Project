package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaFileDTO;
import com.fivet.buddy.mapper.QnaFilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaFileDAO {
    @Autowired
    private QnaFilesMapper qnaFilesMapper;

    public void insertFile(QnaFileDTO qnaFileDto) throws Exception {
        qnaFilesMapper.insertFile(qnaFileDto);
    }

    public List<QnaFileDTO> selectFile(int qnaSeq) throws Exception {
        return qnaFilesMapper.selectFile(qnaSeq);
    }

    public void deleteFile(int qnaSeq) throws Exception {
        qnaFilesMapper.deleteFile(qnaSeq);
    }
}
