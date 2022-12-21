package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.PersonalFolderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface PersonalFolderMapper {
    List<PersonalFolderDTO> selectMyFolders(String memberSeq);
    void insertNewFolder(PersonalFolderDTO personalFolderDto);

    boolean isFolderExists(Map<String, Object> folderCheck);
}
