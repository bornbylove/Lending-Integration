package com.dev.LendingIntegration_.repository.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

    public static AuthRequest getInstance() {
        return new AuthRequest();
    }
}
