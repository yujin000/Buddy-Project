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
    private String chat_Title;
    private int RoomOwnerSeq;
    private int memberCount;
    private Timestamp chatMadeTime;
}
