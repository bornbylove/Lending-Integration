package com.dev.LendingIntegration_.service.serviceImpl;

import com.dev.LendingIntegration_.entity.User;
import com.dev.LendingIntegration_.model.AuthorizationRequests;
import com.dev.LendingIntegration_.model.AuthorizationResponses;
import com.dev.LendingIntegration_.repository.UserRepository;
import com.dev.LendingIntegration_.service.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GetAuthorizationTokenServiceImpl getAuthorizationTokenService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = new User();

         user = userOptional.orElseThrow(()->
                new UsernameNotFoundException(String.format("This user does not exist", username))
        );
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(),
                user.getPassword(),
                Collections.emptyList());
        return userDetails;
    }

    public AuthorizationResponses authenticateUserAndGetToken(AuthorizationRequests authorizationRequest){
        AuthorizationResponses authorizationResponse = new AuthorizationResponses();

        try {
            Optional<User> userOptional = userRepository.findByUsername(authorizationRequest.getUsername());

            if (!userOptional.isPresent()) {
                authorizationResponse.setResponseCode("02");
                authorizationResponse.setResponseMessage("User not found");
                log.info("payloadzz  ::: {}", authorizationResponse.getResponseCode());
                return authorizationResponse;
            }
            User user = userOptional.get();
            String resCode = user.getResponseCode();
            authorizationResponse.setResponseCode(resCode);
            log.info("payloadzzkk  ::: {}", authorizationResponse.getResponseCode());

            if (resCode == null || !authorizationResponse.getResponseCode().equalsIgnoreCase(resCode)) {

                authorizationResponse.setResponseCode("02");
                authorizationResponse.setResponseMessage("Authentication Failed");
                log.info("payload1  ::: {}", authorizationResponse.getResponseCode());
                return authorizationResponse;
            }

            log.info("payload4  ::: {}", authorizationResponse.getResponseCode());
            authorizationResponse.setResponseCode("00");
            authorizationResponse.setResponseMessage("Successful");
            String token = getAuthorizationTokenService.generateToken(authorizationRequest.getUsername());
            authorizationResponse.setAuthToken(token);
            LocalDateTime expiration = authorizationResponse.getExpirationInMinutes();
            authorizationResponse.setExpirationInMinutes(expiration);

            // Return the response with the authorization token
            return authorizationResponse;

        } catch (Exception e) {
            log.error("Exception occurred during authentication", e);
            return new AuthorizationResponses();
        }
    }


}
