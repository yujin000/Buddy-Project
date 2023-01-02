package com.fivet.buddy.dao;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.mapper.BasicFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BasicFolderDAO {

    @Autowired
    private BasicFolderMapper basicFolderMapper;

    // 신규 회원가입 시 기본 폴더 생성
    public void newBasicFolder(Map<String, Object> map) throws Exception{
        basicFolderMapper.newBasicFolder(map);
    };

    // 기본 폴더에 있는 폴더 key값 가져오기
    public String myBasicFolder(int memberSeq) throws Exception{
        return basicFolderMapper.myBasicFolder(memberSeq);
    }

    // 파일 업로드
    public void uploadByte(Map<String,Object> map) {
        basicFolderMapper.uploadByte(map);
    }

    // 사용 용량
    public long myVolume(int memberSeq) {
        return basicFolderMapper.myVolume(memberSeq);
    }

    // 사용 용량 제거
    public long deleteFileByte(Map<String, String> sendMap) {
        return basicFolderMapper.deleteFileByte(sendMap);
    }

    // 기본 폴더 제거
    public void memberOut(int memberSeq) {
        basicFolderMapper.memberOut(memberSeq);
    }

    public String selectBasicKey(int memberSeq) {
        return basicFolderMapper.selectBasicKey(memberSeq);
    }

    // 폴더 삭제시 용량 제거
    public void deleteFolderByte(Map<String, Object> sendMap) {
        basicFolderMapper.deleteFolderByte(sendMap);
    }

    // 팀 생성 시 기본 폴더 생성
    public void newTeamFolder(Map<String, Object> map) {
        basicFolderMapper.newTeamBasicFolder(map);
    }

    // 팀 번호로 key찾기
    public String myTeamFolderKey(int teamSeq) {
        return basicFolderMapper.myTeamFolderKey(teamSeq);
    }
    // 모든 key 출력
    public List<Map<String,String>> allBasicKey(){
        return basicFolderMapper.allBasicKey();
    }

    // team root 용량
    public long getTeamVolume(String rootTeamKey) {
        return basicFolderMapper.getTeamVolume(rootTeamKey);
    }
    // 파일 업로드(팀)
    public void uploadTeamByte(Map<String, Object> map) {
        basicFolderMapper.uploadTeamByte(map);
    }
}
