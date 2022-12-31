package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.CalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CalMapper {

    void insertEvent(CalDTO calDto);

    List<CalDTO> selectAll(int teamSeq);
    List<CalDTO> selectTeam(int teamSeq);
    List<CalDTO> selectPrivate(int teamSeq);

    void deleteEvent(int eventSeq);

    void updateEvent(CalDTO calDto);
}
