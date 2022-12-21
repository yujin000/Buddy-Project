package com.fivet.buddy.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicFolderMapper {

    void newBasicFolder(int basicFolderMemberSeq);
}
