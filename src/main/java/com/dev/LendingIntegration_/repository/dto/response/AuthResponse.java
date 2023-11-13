package com.dev.LendingIntegration_.repository.dto.response;

import lombok.Data;

@Data
public class AuthResponse {

    private String responseCode;
    private String responseMessage;
    private String authToken;
    private String expirationInMinutes;

}
