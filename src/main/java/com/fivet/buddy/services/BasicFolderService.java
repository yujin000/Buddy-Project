package com.fivet.buddy.services;

import com.fivet.buddy.dao.BasicFolderDAO;
import com.fivet.buddy.dao.PersonalFolderDAO;
import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.TeamDTO;
import com.fivet.buddy.util.FileUtil;
import com.fivet.buddy.util.RandomKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicFolderService {

    @Autowired
    private BasicFolderDAO basicFolderDao;

    @Autowired
    private PersonalFolderDAO personalFolderDAO;

    @Autowired
    private RandomKeyUtil randomKeyUtil;

    // 신규 회원가입 시 기본 폴더 생성
    public void newBasicFolder(MemberDTO memberDto) throws Exception{
        // 폴더 key생성
        String basicFolderKey = randomKeyUtil.folderKey();

        Map<String,Object> map = new HashMap<>();

        map.put("basicFolderKey",basicFolderKey);
        map.put("memberSeq",memberDto.getMemberSeq());
        map.put("memberName",memberDto.getMemberName());

        basicFolderDao.newBasicFolder(map);
    };

    // 팀 생성시 기본 폴더 생성
    public void newTeamBasicFolder(TeamDTO teamDto) throws Exception{
        // 폴더 key생성
        String basicFolderKey = randomKeyUtil.folderKey();

        Map<String,Object> map = new HashMap<>();

        map.put("basicFolderKey",basicFolderKey);
        map.put("memberSeq",teamDto.getTeamOwnerSeq());
        map.put("seq",teamDto.getTeamSeq());
        map.put("name",teamDto.getTeamName());

        basicFolderDao.newTeamFolder(map);
    };

    // 파일업로드(개인)
    public void uploadByte(int memberSeq,long fileSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("memberSeq",memberSeq);
        map.put("fileSize",fileSize);

        basicFolderDao.uploadByte(map);
    }

    // 파일 업로드(팀)
    public void uploadTeamByte(String key, long fileSize){
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        map.put("fileSize",fileSize);

        basicFolderDao.uploadTeamByte(map);
    }
    // 내 현재 용량
    public long myVolume(int memberSeq) {
        return basicFolderDao.myVolume(memberSeq);
    }

    // 기본 폴더 제거
    public void memberOut(int memberSeq) {
        // 폴더 key
        String key = basicFolderDao.selectBasicKey(memberSeq);
        // 경로
        String path = personalFolderDAO.myBasicPath(key);
        // 실제 폴더 삭제
        FileUtil fileUtil = new FileUtil();
        fileUtil.deleteFolder(path);
        basicFolderDao.memberOut(memberSeq);
    }

    // 모든 key 출력
    public List<Map<String,String>> allBasicKey(){
        return basicFolderDao.allBasicKey();
    }

    // team root 용량
    public long getTeamVolume(String rootTeamKey) {
        return basicFolderDao.getTeamVolume(rootTeamKey);
    }

    // 팀 삭제 시 기본 폴더 삭제
    public void teamOut(int teamSeq) {
        String path = personalFolderDAO.myBasicPath(basicFolderDao.myTeamFolderKey(teamSeq));
        FileUtil fileUtil = new FileUtil();
        fileUtil.deleteFolder(path);
        basicFolderDao.teamOut(teamSeq);
    }
    // 팀 기본 키 가져오기
    public String myTeamFolderKey(int teamSeq) {
        return basicFolderDao.myTeamFolderKey(teamSeq);
    }


}
