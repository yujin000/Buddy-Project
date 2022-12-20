package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private int teamSeq;
    private String teamName;
    private int teamOwnerSeq;
    private int teamCount;
    private Timestamp teamMadeTime;

}
