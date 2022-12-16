package com.fivet.buddy.services;

import com.fivet.buddy.dao.InviteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    private InviteDAO inviteDAO;
}
