package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.PersonalFolderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalFolderMapper {
    void insertNewFolder(PersonalFolderDTO personalFolderDto);
}
