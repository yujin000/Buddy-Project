package com.fivet.buddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChatFilesDTO {
    private int chatFilesSeq;
    private String chatOriName;
    private String chatSysName;
    private int chatRoomSeq;
    private int chatMsgSeq;
}
