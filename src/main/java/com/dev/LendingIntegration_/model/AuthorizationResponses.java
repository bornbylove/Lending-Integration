package com.dev.LendingIntegration_.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponses {
    private String responseCode;
    private String responseMessage;
    private String authToken;
    long durationInMinutes = 1;
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationInMinutes = currentTime.plusMinutes(durationInMinutes);

}
