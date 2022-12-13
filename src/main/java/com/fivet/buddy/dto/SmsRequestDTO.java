package com.fivet.buddy.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmsRequestDTO {
    String type;
    String contentType;
    String countryCode;
    String from;
    String content;
    List<SmsDTO> messages;
}