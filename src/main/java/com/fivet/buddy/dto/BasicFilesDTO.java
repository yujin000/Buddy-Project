package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicFilesDTO {
    private int filesSeq;
    private String filesOriname;
    private String filesSysname;
    private int filesMemberSeq;
    private int filesFolderSeq;
}
