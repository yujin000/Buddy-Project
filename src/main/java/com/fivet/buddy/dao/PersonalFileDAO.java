package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.PersonalFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalFileDAO {

    @Autowired
    private PersonalFolderMapper personalFolderMapper;
}
