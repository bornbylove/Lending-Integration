package com.dev.LendingIntegration_.controller;

import com.dev.LendingIntegration_.entity.User;
import com.dev.LendingIntegration_.model.AuthorizationRequests;
import com.dev.LendingIntegration_.model.AuthorizationResponses;
import com.dev.LendingIntegration_.service.serviceImpl.AuthUserDetailsServiceImpl;
import com.dev.LendingIntegration_.service.serviceImpl.GetAuthorizationTokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GetPartnerAuthorizationTokenController {
    @Autowired
    private GetAuthorizationTokenServiceImpl getAuthorizationTokenService;

    public GetPartnerAuthorizationTokenController(GetAuthorizationTokenServiceImpl getAuthorizationTokenService) {
        this.getAuthorizationTokenService = getAuthorizationTokenService;
    }


    @PostMapping("/loan/v1/auth/getPartnerAuthorizationToken")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthorizationRequests authorizationRequest) throws Exception {
        log.info("payload  ::: {}", authorizationRequest);

        AuthorizationResponses authorizationResponse = new AuthorizationResponses();
        AuthorizationRequests authorizationRequests = new AuthorizationRequests();
        AuthUserDetailsServiceImpl authUserDetailsService = new AuthUserDetailsServiceImpl();
        User user = new User();
        try {
            log.info("Payload: {}", authorizationRequest);

        if (authorizationRequest == null || StringUtils.isAnyBlank(authorizationRequest.getUsername(), authorizationRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }

        if (authorizationResponse == null || !"00".equals(authorizationResponse.getResponseCode())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        // Generate and set the authorization token
        String token = getAuthorizationTokenService.generateToken(authorizationRequest.getUsername());
        authorizationResponse.setAuthToken(token);

        // Return the response with the authorization token
        return ResponseEntity.ok(authorizationResponse);

    } catch (Exception e) {
        log.error("Exception occurred during authentication", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }


       /* authorizationRequests.setUsername(user.getUsername());
        authorizationRequests.setPassword(user.getPassword());
        authorizationResponse = (AuthorizationResponses) authUserDetailsService.loadUserByUsername(authorizationRequest.getUsername());
        if (authorizationResponse.getResponseCode().equals("00")){
            String token = getAuthorizationTokenService.generateToken(authorizationRequest.getUsername());
            authorizationResponse.setAuthToken(token);
        }
        return ResponseEntity.ok(authorizationResponse);
    }*/


//    @PostMapping("/loan/v1/auth/getPartnerAuthorizationToken")
//    public ResponseEntity<?> getLoanBalance(@RequestBody AuthorizationRequests authorizationRequest) throws Exception{
//        AuthorizationResponses authorizationResponse =new AuthorizationResponses();
//        User user = new User();
//        AuthRequest authRequest = AuthRequest.getInstance();
//        authRequest.setPassword(user.getPassword());
//        authRequest.setUsername(user.getUsername());
//
//        AuthResponse authResponse = authService.authenticate(authRequest);
//        if(authResponse.getResponseCode().equals("00")) {
//            String token = authResponse.getAuthToken();
//
//            //you can now call required endpoint to get loan balance with auth token
//        }
//        return ResponseEntity.ok(authorizationResponse);
//    }
}
}
