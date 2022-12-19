package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDTO {
    private int teamSeq;
    private int memberSeq;
    private String teamMemberNickname;
    private String grade;
    private Timestamp teamJoinDate;
}
