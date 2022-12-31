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

    // Qna 글쓰기
    public void insert(QnaBoardDTO qnaDto) throws Exception{
        qnaBoardMapper.insert(qnaDto);
    }

    //회원이 자신의 Qna 목록 출력
    public List<QnaBoardDTO> select(int qnaWriter) throws Exception{
        return qnaBoardMapper.select(qnaWriter);
    }

    //Qna 삭제
    public void delete(int qnaSeq) throws Exception{
        qnaBoardMapper.delete(qnaSeq);
    }

    // 문의글 본문보기
    public QnaBoardDTO selectDetail(int qnaSeq) {
        return qnaBoardMapper.selectDetail(qnaSeq);
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
