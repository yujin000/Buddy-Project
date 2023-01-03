package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalDTO {

    private int eventSeq;

    private int memberSeq;

    private int teamSeq;
    private String eventName;
    private String eventWriter;
    private String eventType;
    private String eventStart;
    private String eventEnd;
    private String eventLocation;
    private String eventMemo;
    private String eventColor;
}
