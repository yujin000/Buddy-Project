package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberListDTO {
    private int teamSeq;
    private int memberSeq;
    private String memberId;
    private String teamMemberNickname;
    private String grade;
    private Timestamp teamJoinDate;
    private String memberImgSysName;
}
