package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.CalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CalMapper {

    void insertEvent(CalDTO calDto);

    List<CalDTO> selectAll(int teamSeq);

    void deleteEvent(CalDTO calDto);

    void updateEvent(CalDTO calDto);
}
