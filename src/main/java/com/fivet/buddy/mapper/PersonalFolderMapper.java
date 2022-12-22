package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface PersonalFolderMapper {
    List<PersonalFolderDTO> selectMyFolders(Map<String,Object> map);
    void insertNewFolder(PersonalFolderDTO personalFolderDto);

    boolean isFolderExists(Map<String, Object> folderCheck);

    void newPersonalFolder(Map<String, Object> map);

    String searchPath(String parentKey);

    String myBasicFolder(int memberSeq);

    List<PersonalFolderDTO> selectChildFolders(String resourceKey);
}