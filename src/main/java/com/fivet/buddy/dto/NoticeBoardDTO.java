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
public class NoticeBoardDTO {
    private int noticeSeq;
    private int noticeWriterSeq;
    private String noticeWriterName;
    private String noticeTitle;
    private String noticeContents;
    private Timestamp noticeWriteDate;
}
