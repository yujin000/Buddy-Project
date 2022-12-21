package com.fivet.buddy.services;

import com.fivet.buddy.dao.PersonalFolderDAO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class PersonalFolderService {

    @Autowired
    private PersonalFolderDAO personalFolderDao;

    @Autowired
    private HttpSession session;

    // (개인)회원 별 폴더 불러오기
    public List<PersonalFolderDTO> selectMyFolders(String memberSeq) throws Exception{
        return personalFolderDao.selectMyFolders(memberSeq);
    }

    // 새 폴더 추가
    public void insertNewFolder(PersonalFolderDTO personalFolderDto) throws Exception{
        personalFolderDao.insertNewFolder(personalFolderDto);
    }

    // 폴더 존재 유무 체크
    public boolean isFolderExists(String fileName) throws Exception{
        // view에서 넘어온 폴더 이름, 소유한 멤버 번호 Map에 setting
        Map<String, Object> folderCheck = new HashMap<>();
        folderCheck.put("personalFolderName",fileName);
        folderCheck.put("personalFolderMemberSeq",session.getAttribute("memberSeq").toString());

        return personalFolderDao.isFolderExists(folderCheck);
    }

    // 회원가입 시 기본폴더 생성
    public void newPersonalFolder(int memberSeq) throws Exception{

    }

}
