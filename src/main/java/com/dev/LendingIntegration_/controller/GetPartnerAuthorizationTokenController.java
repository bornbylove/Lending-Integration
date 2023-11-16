package com.dev.LendingIntegration_.controller;

import com.dev.LendingIntegration_.model.AuthorizationRequests;
import com.dev.LendingIntegration_.model.AuthorizationResponses;
import com.dev.LendingIntegration_.service.serviceImpl.AuthUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GetPartnerAuthorizationTokenController {
    @Autowired
    @Qualifier("authUserDetailsServiceImpl")
    private AuthUserDetailsServiceImpl authUserDetailsService;



//    @PostMapping("/loan/v1/auth/getPartnerAuthorizationToken")
//    public ResponseEntity<AuthorizationResponses> authenticateUser(@RequestBody AuthorizationRequests authorizationRequest) throws Exception {
//
//        Optional<User> userOptional = userRepository.findByUsername(authorizationRequest.getUsername());
//        User user = userOptional.get();
//        user.getResponseCode();
//        String resCode = user.getResponseCode();
//        authorizationResponse.setResponseCode(resCode);
//
//        try {
//            log.info("Payload: {}", authorizationRequest);
//
//        if (authorizationRequest == null || StringUtils.isAnyBlank(authorizationRequest.getUsername(), authorizationRequest.getPassword())) {
//            authorizationResponse.setResponseCode("01");
//            authorizationResponse.setResponseMessage("Invalid Request Body");
//            return ResponseEntity.badRequest().body(authorizationResponse);
//        }
//
//        if (!authorizationResponse.getResponseCode().equalsIgnoreCase(resCode)) {
//
//            authorizationResponse.setResponseCode("02");
//            authorizationResponse.setResponseMessage("Authentication Failed");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authorizationResponse);
//        }
//
//            log.info("payload4  ::: {}", authorizationResponse.getResponseCode());
//            authorizationResponse.setResponseCode("00");
//            authorizationResponse.setResponseMessage("Successful");
//            String token = getAuthorizationTokenService.generateToken(authorizationRequest.getUsername());
//            authorizationResponse.setAuthToken(token);
//            Date expiration = authorizationResponse.getExpirationInMinutes();
//            authorizationResponse.setExpirationInMinutes(expiration);
//
//        // Return the response with the authorization token
//        return ResponseEntity.ok(authorizationResponse);
//
//    } catch (Exception e) {
//        log.error("Exception occurred during authentication", e);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authorizationResponse);
//    }
//
//
//       /* authorizationRequests.setUsername(user.getUsername());
//        authorizationRequests.setPassword(user.getPassword());
//        authorizationResponse = (AuthorizationResponses) authUserDetailsService.loadUserByUsername(authorizationRequest.getUsername());
//        if (authorizationResponse.getResponseCode().equals("00")){
//            String token = getAuthorizationTokenService.generateToken(authorizationRequest.getUsername());
//            authorizationResponse.setAuthToken(token);
//        }
//        return ResponseEntity.ok(authorizationResponse);
//    }*/
//
//
////    @PostMapping("/loan/v1/auth/getPartnerAuthorizationToken")
////    public ResponseEntity<?> getLoanBalance(@RequestBody AuthorizationRequests authorizationRequest) throws Exception{
////        AuthorizationResponses authorizationResponse =new AuthorizationResponses();
////        User user = new User();
////        AuthRequest authRequest = AuthRequest.getInstance();
////        authRequest.setPassword(user.getPassword());
////        authRequest.setUsername(user.getUsername());
////
////        AuthResponse authResponse = authService.authenticate(authRequest);
////        if(authResponse.getResponseCode().equals("00")) {
////            String token = authResponse.getAuthToken();
////
////            //you can now call required endpoint to get loan balance with auth token
////        }
////        return ResponseEntity.ok(authorizationResponse);
////    }
//}
//}

    @PostMapping("/loan/v1/auth/getPartnerAuthorizationToken")
    public ResponseEntity<AuthorizationResponses> authenticateUser(@RequestBody AuthorizationRequests authorizationRequest) {
        AuthorizationResponses authorizationResponse = authUserDetailsService.authenticateUserAndGetToken(authorizationRequest);
        if ("01".equals(authorizationResponse.getResponseCode())) {
            System.out.println("my payload link");
            log.info("payload1  ::: {}", authorizationResponse.getResponseCode());
            return ResponseEntity.badRequest().body(authorizationResponse);
        } else if ("02".equals(authorizationResponse.getResponseCode())) {
            log.info("payload2  ::: {}", authorizationResponse.getResponseCode());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authorizationResponse);
        } else if ("00".equals(authorizationResponse.getResponseCode())) {
            log.info("payload3  ::: {}", authorizationResponse.getResponseCode());
            return ResponseEntity.ok(authorizationResponse);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authorizationResponse);
        }
    }
}
