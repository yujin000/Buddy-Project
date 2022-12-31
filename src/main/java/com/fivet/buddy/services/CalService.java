package com.fivet.buddy.services;

import com.fivet.buddy.dao.CalDAO;
import com.fivet.buddy.dto.CalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalService {
    @Autowired
    private CalDAO calDao;

    public void insertEvent(CalDTO calDto) throws Exception{
         calDao.insertEvent(calDto);
    }

    public List<CalDTO> selectAll(int teamSeq) throws Exception{
        return calDao.selectAll(teamSeq);
    }

    public List<CalDTO> selectTeam(int teamSeq) throws Exception{
        return calDao.selectTeam(teamSeq);
    }

    public List<CalDTO> selectPrivate(int teamSeq) throws Exception{
        return calDao.selectPrivate(teamSeq);
    }

    public String selectGrade(String teamMemberNickname) throws Exception{
        return calDao.selectGrade(teamMemberNickname);
    }

    public void deleteEvent(int eventSeq) throws Exception{
        calDao.deleteEvent(eventSeq);
        }

        public void updateEvent(CalDTO calDto) throws Exception{
        calDao.updateEvent(calDto);
        }


}
