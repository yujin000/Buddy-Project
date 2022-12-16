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
    private int inviteSendmemSeq;
    private String inviteReceivememEmail;
    private String inviteCode;
    private String inviteUsageStatus;
}
