package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.QnaFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaFilesMapper {
    void insertFile(QnaFileDTO qnaFileDto);
    List<QnaFileDTO> selectFile(int qnaSeq);
    void deleteFile(int qnaSeq);

}
