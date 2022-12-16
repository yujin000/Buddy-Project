package com.fivet.buddy.dao;

import com.fivet.buddy.mapper.InviteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDAO {
    @Autowired
    private InviteMapper inviteMapper;
}
