package com.fivet.buddy.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fivet.buddy.dao.BasicFolderDAO;
import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.util.RandomKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
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

}
