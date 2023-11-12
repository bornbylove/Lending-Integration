package com.dev.LendingIntegration_.controller;

import com.dev.LendingIntegration_.entity.User;
import com.dev.LendingIntegration_.model.AuthorizationRequests;
import com.dev.LendingIntegration_.model.AuthorizationResponses;
import com.dev.LendingIntegration_.service.serviceImpl.GetAuthorizationTokenServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/loan/v1")
@Slf4j
public class GetPartnerAuthorizationTokenController {

    private GetAuthorizationTokenServiceImpl getAuthorizationTokenService;
    private AuthorizationResponses authorizationResponse;
    private User user;

    @PostMapping("/getPartnerAuthorizationToken")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthorizationRequests authorizationRequest){
        log.info("payload  ::: {}", authorizationRequest);

        String token = getAuthorizationTokenService.generateToken(user.getUsername());

        authorizationResponse.setAuthToken(token);
        return ResponseEntity.ok(authorizationResponse);
    }
}
