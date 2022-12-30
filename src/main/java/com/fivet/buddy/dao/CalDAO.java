package com.fivet.buddy.dao;

import com.fivet.buddy.dto.CalDTO;
import com.fivet.buddy.mapper.CalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalDAO {
    @Autowired
    private CalMapper calMapper;

    public void insertEvent(CalDTO calDto) throws Exception{
         calMapper.insertEvent(calDto);
    }

    public List<CalDTO> selectAll(int teamSeq) throws Exception{
        return calMapper.selectAll(teamSeq);
    }

    public void deleteEvent(CalDTO calDto) throws Exception{
        calMapper.deleteEvent(calDto);
    }

    public  void updateEvent(CalDTO calDto) throws  Exception {
        calMapper.updateEvent(calDto);
    }
}
