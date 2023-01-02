package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

//채팅방에서 채팅 회원 목록을 출력하기 위한 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMemberListDTO {
    private int chatRoomSeq;
    private int memberSeq;
    private int teamSeq;
    private Timestamp chatJoinDate;
    private Timestamp recentViewDate;
    private String teamMemberNickname;
    private String grade;
    private String memberImgSysName;

}
