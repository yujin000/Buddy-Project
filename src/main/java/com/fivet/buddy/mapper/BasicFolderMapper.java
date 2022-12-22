package com.fivet.buddy.mapper;

import com.fivet.buddy.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BasicFolderMapper {

    void newBasicFolder(Map<String,Object> map);

    String myBasicFolder(int memberSeq);
}
