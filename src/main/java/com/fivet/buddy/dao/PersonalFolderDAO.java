package com.fivet.buddy.dao;

import com.fivet.buddy.dto.PersonalFolderDTO;
import com.fivet.buddy.mapper.PersonalFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.mail.Folder;
import java.util.List;
import java.util.Map;

@Repository
public class PersonalFolderDAO {

    @Autowired
    private PersonalFolderMapper personalFolderMapper;


    // (개인)회원 별 폴더 불러오기
    public List<PersonalFolderDTO> selectMyFolders(Map<String,Object> map) throws Exception{
        return personalFolderMapper.selectMyFolders(map);
    }

    // 폴더 생성
    public void insertNewFolder(PersonalFolderDTO personalFolderDto) throws Exception{
        personalFolderMapper.insertNewFolder(personalFolderDto);
    }

    // 폴더 존재 유무 체크
    public boolean isFolderExists(Map<String, Object> folderCheck) throws Exception{
        return personalFolderMapper.isFolderExists(folderCheck);
    }

    // 회원가입 시 personal foler 테이블에 기본폴더 생성
    public void newPersonalFolder(Map<String, Object> map) throws Exception{
        personalFolderMapper.newPersonalFolder(map);
    }

    // 부모 폴더 경로 찾아오기(폴더 생성)
    public String searchPath(String parentKey) throws Exception{
        return personalFolderMapper.searchPath(parentKey);
    }

    // personal_folder 테이블에서 내 기본 폴더key 추출
    public String myBasicFolder(int memberSeq) throws Exception{
        return personalFolderMapper.myBasicFolder(memberSeq);
    }

    public List<PersonalFolderDTO> selectChildFolders(String resourceKey) {
        return personalFolderMapper.selectChildFolders(resourceKey);
    }

    // 현재 폴더 이름
    public String nowFolder(String resourceKey) {
        return personalFolderMapper.nowFolder(resourceKey);
    }

    // 폴더 삭제
    public void deleteFolder(List<Map<String, String>> folders) {
        personalFolderMapper.deleteFolder(folders);
    }

    // 현재 폴더 경로
    public String myPath(String key) {
        return personalFolderMapper.myPath(key);
    }

    // 폴더 삭제(경로)
    public void deleteFolderByPath(String path) {
        personalFolderMapper.deleteFolderByPath(path);
    }

    // 폴더 밑에 하위폴더가 있는지 확인
    public boolean subIsExist(String folderKey) {
        return personalFolderMapper.subIsExist(folderKey);
    }

    // 폴더 주인 시퀀스
    public PersonalFolderDTO getFolderOwner(String resourceKey) {
        return personalFolderMapper.getFolderOwner(resourceKey);
    }

    // 권한 수정
    public void accessStatus(Map<String,String> map) {
        personalFolderMapper.accessStatus(map);
    }

    // personal_folder 테이블에 update
    public void updateMyFolderByte(Map<String, Object> map) {
        personalFolderMapper.updateMyFolderByte(map);
    }

    // 폴더 정보 가져오기
    public PersonalFolderDTO myFolderInfo(String key) {
        return personalFolderMapper.myFolderInfo(key);
    }

    // 폴더 삭제 하기
    public void memberOut(int memberSeq) {
        personalFolderMapper.memberOut(memberSeq);
    }

    // 삭제하려는 폴더 용량 가져오기
    public long getMyByte(String key) {
        return personalFolderMapper.getMyByte(key);
    }

    // 파일 삭제 시 폴더 용량 제거
    public void deleteFileByte(Map<String, String> sendMap) {
        personalFolderMapper.deleteFileByte(sendMap);
    }

    // 기존 파일 경로
    public String myBasicPath(String key) {
        return personalFolderMapper.myBasicPath(key);
    }
}
