package com.fivet.buddy.dao;

import com.fivet.buddy.dto.PersonalFileDTO;
import com.fivet.buddy.mapper.PersonalFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    // 폴더 하위 파일들 선택
    public List<Map<String,String>> folderChildFiles(String key) {
        return personalFileMapper.folderChildFiles(key);
    }
}
