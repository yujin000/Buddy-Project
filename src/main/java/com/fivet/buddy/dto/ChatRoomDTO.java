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
public class ChatRoomDTO {

    private int chatRoomSeq;
    private String chatTitle;
    private int teamSeq;
    private int roomOwnerSeq;
    private String chatType;
    private int memberCount;
    private Timestamp chatMadeTime;
}
