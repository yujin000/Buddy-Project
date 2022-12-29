package com.fivet.buddy.services;

import com.fivet.buddy.dao.BasicFolderDAO;
import com.fivet.buddy.dto.MemberDTO;
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

    // 파일업로드
    public void uploadByte(int memberSeq,int fileSize) {
        Map<String,Integer> map = new HashMap<>();
        map.put("memberSeq",memberSeq);
        map.put("fileSize",fileSize);

        basicFolderDao.uploadByte(map);
    }

}
