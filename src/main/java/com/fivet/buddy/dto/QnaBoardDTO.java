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
public class QnaBoardDTO {
private int qnaSeq;
private int qnaWriter;
private String qnaTitle;
private String qnaContents;
private Timestamp qnaWriteDate;
private String qnaType;

}
