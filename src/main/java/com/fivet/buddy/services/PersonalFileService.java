package com.fivet.buddy.services;

import com.fivet.buddy.dao.PersonalFolderDAO;
import com.fivet.buddy.dto.PersonalFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PersonalFileService {

    @Autowired
    private PersonalFolderDAO personalFolderDAO;

    @Autowired
    private HttpSession session;

    public void uploadFromBtn(String oriName, String sysName) throws Exception{
//        PersonalFileDTO personalFileDto = new PersonalFileDTO();
//        personalFileDto.setPersonalFilesSeq(Integer.parseInt(session.getAttribute("memberSeq").toString());
//        personalFileDto.setPersonalFilesOriname(oriName);
//        personalFileDto.setPersonalFilesSysname(sysName);
        // 폴더 seq값을 넘겨받아야함(제일 처음이면 첫 폴더)
        // personalFileDto.setPersonalFilesFolderSeq();
        // personalFolderDAO.uploadFromBtn(personalFileDto);
    }
}
