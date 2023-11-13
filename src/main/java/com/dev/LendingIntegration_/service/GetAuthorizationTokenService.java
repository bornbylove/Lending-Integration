package com.dev.LendingIntegration_.service;

import com.dev.LendingIntegration_.service.serviceImpl.GetAuthorizationTokenServiceImpl;

public interface GetAuthorizationTokenService{
    public String generateToken(String username);
    public Boolean validateToken(String token);
    public String extractUsername(String token);
}
