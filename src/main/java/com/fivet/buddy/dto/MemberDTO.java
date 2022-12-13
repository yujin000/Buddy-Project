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
public class MemberDTO {
    private int memberSeq;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberPhone;
    private Timestamp memberSignupDate;
    private String memberLogtype;
}
