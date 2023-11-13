package com.dev.LendingIntegration_.service.serviceImpl;

import com.dev.LendingIntegration_.entity.User;
import com.dev.LendingIntegration_.repository.UserRepository;
import com.dev.LendingIntegration_.service.AuthUserDetailsService;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService {

private UserRepository userRepository;

    //@Override
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
}
