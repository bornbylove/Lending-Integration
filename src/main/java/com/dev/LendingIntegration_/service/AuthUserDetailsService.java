package com.dev.LendingIntegration_.service;

import com.dev.LendingIntegration_.model.AuthorizationRequests;
import com.dev.LendingIntegration_.model.AuthorizationResponses;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthUserDetailsService extends UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException;

    public AuthorizationResponses authenticateUserAndGetToken(AuthorizationRequests authorizationRequest);
}
