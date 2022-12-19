package com.fivet.buddy.services;

import com.fivet.buddy.dao.TeamDAO;
import com.fivet.buddy.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class TeamService {

    @Autowired
    TeamDAO teamDao;



//    팀 생성
    public void insert (TeamDTO teamDto) {

        teamDao.insert(teamDto);
    }
}
