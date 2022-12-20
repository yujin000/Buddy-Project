package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFolderDTO {
    private int PersonalFolderSeq;
    private String PersonalFolderName;
    private int PersonalFolderMemberSeq;
}
