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
public class ChatMemberDTO {
    private int chatRoomSeq;
    private int memberSeq;
    private Timestamp chatJoinDate;
    private Timestamp recentViewDate;
}