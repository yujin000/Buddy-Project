package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.TeamDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper {
    void insertTeam(TeamDTO dto);
}
