package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeFileDTO {
    private int noticeFilesSeq;
    private String noticeOriname;
    private String noticeSysname;
    private int noticeSeq;
}
