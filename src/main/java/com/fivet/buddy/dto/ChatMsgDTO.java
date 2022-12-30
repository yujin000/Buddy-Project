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
public class ChatMsgDTO {
    private int chatSeq;
    private int chatRoomSeq;
    private String teamMemberNickname;
    private String chatContent;
    private int memberSeq;
    private int readCount;
    private int chatMsgType;
    private Timestamp chatDate;
}
