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

    public int insertFile(QnaFileDTO qnaFileDto) throws Exception {
        return qnaMapper.insertFile(qnaFileDto);
    }

    public List<QnaFileDTO> selectFile(int qnaSeq) throws Exception{
        return qnaMapper.selectFile(qnaSeq);
    }

    public int deleteFile(int qnaSeq) throws Exception{
        return qnaMapper.deleteFile(qnaSeq);
    }
}
