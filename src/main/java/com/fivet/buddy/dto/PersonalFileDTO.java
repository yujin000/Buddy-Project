package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFileDTO {
    private int personalFilesSeq;
    private String personalFilesOriname;
    private String personalFilesSysname;
    private int personalFilesMemberSeq;
    private int personalFilesFolderSeq;
}
