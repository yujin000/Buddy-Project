package com.fivet.buddy.dao;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.mapper.PersonalFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Map;

@Repository
public class PersonalFileDAO {

    @Autowired
    private PersonalFileMapper personalFileMapper;

    // 파일첨부
    public void uploadFile(PersonalFileDTO personalFileDto) throws Exception{
        personalFileMapper.uploadFile(personalFileDto);
    }

    // 내 파일 찾기
    public List<PersonalFileDTO> selectMyFiles(Map<String, Object> map) throws Exception{
        return personalFileMapper.selectMyFiles(map);
    }

    // 하위 폴더 파일 찾기
    public List<PersonalFileDTO> selectChildFiles(String resourceKey) throws Exception{
        return personalFileMapper.selectChildFiles(resourceKey);
    }

    // 파일 삭제
    public void deleteFile(List<Map<String, String>> fileList) throws Exception{
        personalFileMapper.deleteFile(fileList);
    }

    // 파일 경로 불러오기
    public String myPath(String parentKey) {
        return personalFileMapper.myPath(parentKey);
    }

    // 파일 경로로 삭제
    public void deleteByPath(String path){
        personalFileMapper.deleteByPath(path);
    }

    public List<Map<String, String>> getFilePath(List<Map<String, String>> files) {
        return personalFileMapper.getFilePath(files);
    }

    // OriName 찾기
    public String thisOriName(String key) {
        return personalFileMapper.thisOriName(key);
    }

    // 폴더 경로
    public String searchPath(String key) {
        return personalFileMapper.searchPath(key);
    }

    // 폴더 정보
    public PersonalFileDTO myFileInfo(String key) {
        return personalFileMapper.myFileInfo(key);
    }

    // 회원 sequence로 파일 삭제
    public void memberOut(int memberSeq) {
        personalFileMapper.memberOut(memberSeq);
    }
}
