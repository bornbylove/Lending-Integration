package com.dev.LendingIntegration_.service;

import com.dev.LendingIntegration_.repository.dto.request.AuthRequest;
import com.dev.LendingIntegration_.repository.dto.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    public AuthResponse authenticate(AuthRequest authRequest) {
        String apiUrl = "accessBankBaseUrl" + "/loan/v1/getPartnerAuthorizationToken";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthResponse> responseEntity =
                restTemplate.postForEntity(apiUrl, authRequest, AuthResponse.class);
        AuthResponse authResponse = responseEntity.getBody();
        return new AuthResponse();
    }
}
