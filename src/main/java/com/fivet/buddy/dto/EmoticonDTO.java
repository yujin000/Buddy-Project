package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmoticonDTO {
    private int emoticonSeq;
    private String emoticonOriName;
    private String emoticonSysName;
}
