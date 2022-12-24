package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.PersonalFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonalFileMapper {
    void uploadFile(PersonalFileDTO personalFileDto);

    List<PersonalFileDTO> selectMyFiles(Map<String, Object> map);

    List<PersonalFileDTO> selectChildFiles(String resourceKey);
}
