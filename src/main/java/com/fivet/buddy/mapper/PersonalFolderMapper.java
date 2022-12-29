package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import org.apache.ibatis.annotations.Mapper;

import javax.mail.Folder;
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

    String nowFolder(String resourceKey);

    void deleteFolder(List<Map<String, String>> folders);

    String myPath(String key);

    void deleteFolderByPath(String path);

    boolean subIsExist(String folderKey);

    PersonalFolderDTO getFolderOwner(String resourceKey);

    void accessStatus(Map<String,String> map);
}
