package com.fivet.buddy.dao;

import com.fivet.buddy.dto.QnaBoardDTO;
import com.fivet.buddy.mapper.QnaBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QnaBoardDAO {
    @Autowired
    private QnaBoardMapper qnaBoardMapper;

    public void insert(QnaBoardDTO qnaDto) throws Exception{
        qnaBoardMapper.insert(qnaDto);
    }
    public List<QnaBoardDTO> select(int qnaWriter) throws Exception{
        return qnaBoardMapper.select(qnaWriter);
    }

    public void delete(int qnaSeq) throws Exception{
        qnaBoardMapper.delete(qnaSeq);
    }

    // Qna 모든 글 출력
    public List<QnaBoardDTO> selectQnaBoardAll() {
        return qnaBoardMapper.selectQnaBoardAll();
    }

    // Qna글 페이지에 맞춰 출력
    public List<QnaBoardDTO> selectQnaBoardPage(Map<String, Integer> param) {
        return qnaBoardMapper.selectQnaBoardPage(param);
    }

    //Qna 전체 글 수 출력
    public int totalCount() {
        return qnaBoardMapper.totalCount();
    }
}
