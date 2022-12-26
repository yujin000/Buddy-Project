package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.PersonalFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonalFileMapper {
    void uploadFile(PersonalFileDTO personalFileDto);

    List<PersonalFileDTO> selectMyFiles(Map<String, Object> map);

    List<PersonalFileDTO> selectChildFiles(String resourceKey);

    void deleteFile(@Param("list") List<Map<String, String>> fileList);

    String myPath(String parentKey);

    List<Map<String,String>> folderChildFiles(String key);
}
