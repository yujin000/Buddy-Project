package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.CalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CalMapper {

    void insertEvent(CalDTO calDto);

    List<CalDTO> selectAll(int teamSeq);
    List<CalDTO> selectTeam(int teamSeq);
    List<CalDTO> selectPrivate(int memberSeq, int teamSeq);

    String selectGrade(String teamMemberNickname);

    void deleteEvent(int eventSeq, int memberSeq);

    void updateEvent(CalDTO calDto);
}
