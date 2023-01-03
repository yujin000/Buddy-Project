package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import com.fivet.buddy.dto.PersonalFolderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    void updateMyFolderByte(Map<String, Object> map);

    PersonalFolderDTO myFolderInfo(String key);

    void memberOut(int memberSeq);

    long getMyByte(String key);

    void deleteFileByte(Map<String, String> sendMap);

    String myBasicPath(String key);

    void newTeamSubFolder(Map<String, Object> map);

    List<PersonalFolderDTO> getRootTeamFolder(List<Map<String, Integer>> teamSeqList);

    boolean isTeam(String parentKey);

    PersonalFolderDTO pathAndType(String thisFolderKey);

    int getTeamSeq(String resourceKey);

    String getRootTeamKey(int folderTeamSeq);

    String getParentKey(String searchKey);

    List<Map<String, String>> nameList(String parentKey);

    List<Map<String,String>> getTeamKeys(@Param("list") List<Map<String,String>> keyList);

    void teamOut(@Param("list") List<Map<String, String>> allKeys);
}
