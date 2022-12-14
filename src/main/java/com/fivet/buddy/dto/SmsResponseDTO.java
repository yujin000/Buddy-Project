package com.fivet.buddy.dto;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmsResponseDTO {
    String requestId;
    LocalDateTime requestTime;
    String statusCode;
    String statusName;
}
