package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InviteDTO {
    private int inviteTeamSeq;
    private int inviteSendMemSeq;
    private String inviteReceiveMemEmail;
    private String inviteCode;
}
