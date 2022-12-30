package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicFolderDTO {
    private String basicFolderKey;
    private int basicFolderMemberSeq;
    private String basicFolderName;
    private long basicFolderByte;
}
