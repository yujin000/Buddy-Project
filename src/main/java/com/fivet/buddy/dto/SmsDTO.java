package com.fivet.buddy.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmsDTO {
    String to;
    String content;
}
