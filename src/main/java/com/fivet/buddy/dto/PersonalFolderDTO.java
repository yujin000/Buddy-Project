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
public class PersonalFolderDTO {
    private String personalFolderKey;
    private String personalFolderName;
    private int personalFolderMemberSeq;
    private String personalFolderParentKey;
    private String personalFolderPath;
    private Timestamp personalFolderDate;
    private String personalFolderShared;
    private long personalFolderByte;
    private String personalFolderTeamSeq;
    private String personalFolderType;
}
