package com.dev.LendingIntegration_.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequests {
    @NotBlank
    @Size(max = 25, message = "Username should not be greater than 25 characters")
    private String username;
    @NotBlank
    @Size(min = 8, message = "Password should not be less than 8 characters")
    private String password;
}
